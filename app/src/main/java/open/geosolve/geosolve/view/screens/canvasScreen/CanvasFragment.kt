package open.geosolve.geosolve.view.screens.canvasScreen

import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import kotlinx.coroutines.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.view.MvpFragmentX


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter(app) }

    override fun setupLayout() {

        layout.canvas.attachFigure(app.figure)
        layout.canvas.onTouchUp = { x, y -> presenter.onTouchUp(x, y) }
        layout.canvas.onTouchDown = { x, y -> presenter.onTouchDown(x, y) }
        layout.canvas.onTouchMove = { x, y -> presenter.onTouchMove(x, y) }

        layout.calc_button.setOnClickListener {
            presenter.solveButtonClicked()
        }

        layout.mark_mode_button.setOnClickListener {
            presenter.markButtonClicked()
        }

        layout.edit_mode_button.setOnClickListener {
            presenter.editButtonClicked()
        }

        layout.delete_mode_button.setOnClickListener {
            presenter.deleteButtonClicked()
        }

        layout.set_value_mode_button.setOnClickListener {
            presenter.setValueClicked()
        }

        layout.clear_button.setOnClickListener {
            presenter.clearButtonClicked()
        }

        layout.move_mode_button.setOnClickListener{
            presenter.moveButtonClicked()
        }

        GlobalScope.launch(Dispatchers.Main) {
            delay(50)
            App.widthCanvas = canvas.width
            App.heightCanvas = canvas.height

            showTypeFigure()
            updateCanvas()
        }
    }

    override fun goToSolveScreen() {
        findNavController().navigate(R.id.action_to_solve)
    }

    override fun updateCanvas() {
        canvas.invalidate()
    }

    override fun showDialog(titleID: Int, inputCallback: (value: Float) -> Unit) {
        AlertDialog.Builder(activity)
            .setTitle(getText(titleID))
            .setView(
                LayoutInflater.from(activity).inflate(R.layout.dialog_input_value, null)
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

    override fun showTypeFigure() {
        val typeFigure = if (SolveUtil.typeSolve::class.simpleName != "UnknownFigure")
            SolveUtil.typeSolve::class.simpleName
        else
            ""
        val subTypeFigure = if (SolveUtil.subTypeSolve::class.simpleName != "UnknownFigure")
            SolveUtil.subTypeSolve::class.simpleName
        else
            ""

        text_type_figure.text =  if (subTypeFigure != "")
            "$typeFigure : $subTypeFigure"
        else
            typeFigure

        // TODO(DELETE THIS DEBUGGER)
        DELETE_THIS_DEBUGGER.text = app.figure.toString()
    }

    override fun showMessage(messageID: Int) {
        Toast.makeText(context, messageID, Toast.LENGTH_SHORT).show()
    }
}