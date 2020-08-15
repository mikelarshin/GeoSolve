package open.geosolve.geosolve.view.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.AllAngles
import open.geosolve.geosolve.GlobalFiguresController.FigureList
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.math.MathUtil.getDegree
import open.geosolve.geosolve.model.tools.AddTool
import open.geosolve.geosolve.model.tools.AddTool.setNodeChars
import open.geosolve.geosolve.model.tools.DeleteTool
import open.geosolve.geosolve.model.tools.MarkTool
import open.geosolve.geosolve.model.tools.SetValueTool
import open.geosolve.geosolve.presentation.presenter.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.view.DesignUtil
import java.util.*
import kotlin.math.absoluteValue


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter() }

    @SuppressLint("ClickableViewAccessibility")
    override fun setupLayout() {

        layout.touchCanvasView.onTouchUp = { x, y -> presenter.onTouchUp(x, y) }
        layout.touchCanvasView.onTouchDown = { x, y -> presenter.onTouchDown(x, y) }
        layout.touchCanvasView.onTouchMove = { x, y -> presenter.onTouchMove(x, y) }

        layout.show_solve_button.setOnClickListener {
            presenter.showSolveClick()
        }

        layout.mark_mode_button.setOnClickListener {
            presenter.tool = MarkTool
        }

        layout.mark_mode_button.setOnTouchListener { v, event ->
            v.onTouchEvent(event)

            when (event.action) {
                MotionEvent.ACTION_DOWN -> presenter.onPressMark()
            }

            true
        }

        layout.edit_mode_button.setOnClickListener {
            presenter.tool = AddTool
        }

        layout.delete_mode_button.setOnClickListener {
            presenter.tool = DeleteTool
        }

        layout.set_value_mode_button.setOnClickListener {
            presenter.tool = SetValueTool
        }

        layout.clear_button.setOnClickListener {
            presenter.clearButtonClick()
        }

        setupFigure()
    }

    fun setupFigure() {
        AddTool.cycleTouch(-10f, -10f)
        AddTool.cycleTouch(10f, -10f)
        AddTool.cycleTouch(-10f, 10f)
        AddTool.cycleTouch(-10f, -10f)

        val angleList: List<Angle> = AllAngles.toList()

        angleList[0].setDependentValueDraw { getDegree(angleList[0].startNode, angleList[0].angleNode, angleList[0].finalNode).absoluteValue }
        angleList[1].setDependentValueDraw { getDegree(angleList[1].startNode, angleList[1].angleNode, angleList[1].finalNode).absoluteValue }
        angleList[2].setDependentValueDraw { 180f - (angleList[0].getValue()!! + angleList[1].getValue()!!) }

        setNodeChars()
    }

    override fun goToSolveScreen(solveList: List<SolveGraph>) {
        solveList as ArrayList<SolveGraph>
        val action = CanvasFragmentDirections.actionToSolve(solveList)
        findNavController().navigate(action)
    }

    override fun updateCanvas() {
        touchCanvasView.invalidate()
    }

    override fun showMessage(messageID: Int) {
        Toast.makeText(context, messageID, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(titleID: Int, element: String, inputCallback: (value: Float) -> Unit) {
        val alertMessage = DesignUtil.formatAlertMessage(titleID, element)

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
}
