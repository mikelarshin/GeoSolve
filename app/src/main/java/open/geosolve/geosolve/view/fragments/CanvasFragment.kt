package open.geosolve.geosolve.view.fragments

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.FigureList
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.tools.*
import open.geosolve.geosolve.presentation.presenter.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.view.formatAlertMessage
import open.geosolve.geosolve.view.inflate
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.canvas.TouchCanvasView
import java.util.*


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter() }
    private var tool: Tool = AddTool
    private var dataCanvas = CanvasData()
    private lateinit var canvasView: TouchCanvasView

    @SuppressLint("ClickableViewAccessibility")
    override fun setupLayout() {
        canvasView = layout.canvasView
        canvasView.dataCanvas = dataCanvas
        canvasView.canvasPresenter.tool = tool
        canvasView.canvasPresenter.canvasScreenPresenter = presenter

        layout.show_solve_button.setOnClickListener {
            presenter.showSolveClick()
        }

        layout.clear_button.setOnClickListener {
            presenter.clearButtonClick()
        }

        setToolButton(layout.mark_mode_button, MarkTool)
        setToolButton(layout.edit_mode_button, AddTool)
        setToolButton(layout.delete_mode_button, DeleteTool)
        setToolButton(layout.set_value_mode_button, SetValueTool)

        layout.mark_mode_button.setOnTouchListener { v, event ->
            v.onTouchEvent(event)

            if (event.action == MotionEvent.ACTION_DOWN) {
                // Площадь и периметр
                // TODO(implement this)
            }

            true
        }

//        FOR_TEST()
    }
//    fun FOR_TEST() { // TODO(DELETE IT IS)
//        dataCanvas.selectIt()

//        makeTriangleOne()
//        MarkTool.cycleTouch(-7f, 7f)
//
//        val angleList: List<Angle> = AllAngles.toList()
//        angleList[0].setValueDraw(90f)
//        angleList[1].setValueDraw(60f)

//        makeCircle()
//        val circle = AllCircles.toList()[0]
//        circle.setDependentValueDraw { circle.radius }
//
//        presenter.solveAndCallBack()
//    }

    private fun setToolButton(button: FloatingActionButton, tool: Tool) {
        button.setOnClickListener {
            canvasView.canvasPresenter.tool = tool
            this.tool = tool
        }
    }

    override fun goToSolveScreen(solveList: List<SolveGraph>) {
        solveList as ArrayList<SolveGraph>
        val action = CanvasFragmentDirections.actionToSolve(solveList, dataCanvas)
        findNavController().navigate(action)
    }

    override fun updateCanvas() {
        canvasView.invalidate()
    }

    override fun showMessage(messageID: Int) {
        Toast.makeText(context, messageID, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(titleID: Int, element: String, inputCallback: (value: Float) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())

        builder.apply {
            val alertMessage = formatAlertMessage(titleID, element)
            setTitle(alertMessage)
            setView(context.inflate(R.layout.dialog_input_value))

            setPositiveButton(android.R.string.ok) { dialog, _ ->
                if (!(dialog as AlertDialog).input.text.isNullOrBlank())
                    inputCallback(dialog.input.text.toString().toFloat())
            }
            setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }

            show()
        }
    }

    override fun showTypeFigure() { // TODO(DELETE_THIS_DEBUGGER)
        DELETE_THIS_DEBUGGER.text = FigureList.joinToString(separator = "\n\n") { "$it" }
    }
}
