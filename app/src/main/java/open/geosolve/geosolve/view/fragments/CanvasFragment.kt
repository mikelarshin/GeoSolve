package open.geosolve.geosolve.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
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
import open.geosolve.geosolve.model.AllAngles
import open.geosolve.geosolve.model.FigureList
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.math.MathUtil
import open.geosolve.geosolve.model.tools.*
import open.geosolve.geosolve.presentation.presenter.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.view.formatAlertMessage
import open.geosolve.geosolve.view.fragments.CanvasFragmentArgs.fromBundle
import open.geosolve.geosolve.view.views.canvas.CanvasData
import java.util.*
import kotlin.math.absoluteValue


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter() }
    private var dataCanvas = CanvasData()

    @SuppressLint("ClickableViewAccessibility")
    override fun setupLayout() {
        layout.canvasView.dataCanvas = dataCanvas
        layout.canvasView.canvasPresenter.canvasScreenPresenter = presenter

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

        FOR_TEST()
    }

    fun FOR_TEST() { // TODO(DELETE THAT IS)
        dataCanvas.selectIt()
        AddTool.cycleTouch(-10f, -10f)
        AddTool.cycleTouch(10f, -10f)
        AddTool.cycleTouch(-10f, 10f)
        AddTool.cycleTouch(-10f, -10f)
        MarkTool.cycleTouch(-7f, -7f)

        val angleList: List<Angle> = AllAngles.toList()
        angleList[0].setValueDraw(90f)
        angleList[1].setValueDraw(60f)

        presenter.solveAndCallBack()
    }

    private fun setToolButton(button: FloatingActionButton, tool: Tool) {
        button.setOnClickListener {
            layout.canvasView.canvasPresenter.tool = tool
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
        val alertMessage = formatAlertMessage(titleID, element)

        AlertDialog.Builder(activity!!)
            .setTitle(alertMessage)
            .setView(
                LayoutInflater.from(activity!!).inflate(R.layout.dialog_input_value, null)
            )
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                if (!(dialog as AlertDialog).input.text.isNullOrBlank())
                    inputCallback(dialog.input.text.toString().toFloat())
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun showTypeFigure() { // TODO(DELETE_THIS_DEBUGGER)
        DELETE_THIS_DEBUGGER.text = FigureList.joinToString(separator = "\n\n") { "$it" }
    }

    override fun saveData(bundle: Bundle) {
        bundle.putSerializable("dataCanvas", layout.canvasView.dataCanvas)
    }

    override fun setupData(bundle: Bundle) {
        val args = fromBundle(bundle)
        dataCanvas = args.dataCanvas
        layout.canvasView.dataCanvas = dataCanvas
    }
}
