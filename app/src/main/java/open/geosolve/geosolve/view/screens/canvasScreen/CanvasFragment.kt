package open.geosolve.geosolve.view.screens.canvasScreen

import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.GlobalFiguresController.figureList
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.status.Mode
import open.geosolve.geosolve.presentation.presenter.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.view.MvpFragmentX
import open.geosolve.geosolve.view.screens.DesignUtil


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter(app) }

    override fun setupLayout() {

        layout.canvas.onTouchUp = { x, y -> presenter.onTouchUp(x, y) }
        layout.canvas.onTouchDown = { x, y -> presenter.onTouchDown(x, y) }
        layout.canvas.onTouchMove = { x, y -> presenter.onTouchMove(x, y) }

        layout.show_solve_button.setOnClickListener {
            presenter.showSolveClick()
        }

        layout.mark_mode_button.setOnClickListener {
            presenter.setMode(Mode.MARK_FIND)
        }

        layout.edit_mode_button.setOnClickListener {
            presenter.setMode(Mode.ADD)
        }

        layout.delete_mode_button.setOnClickListener {
            presenter.setMode(Mode.DELETE)
        }

        layout.set_value_mode_button.setOnClickListener {
            presenter.setMode(Mode.SET_VALUE)
        }

        layout.clear_button.setOnClickListener {
            presenter.clearButtonClicked()
        }
    }

    override fun goToSolveScreen() {
        findNavController().navigate(R.id.action_to_solve)
    }

    override fun updateCanvas() {
        canvas.invalidate()
    }

    override fun showMessage(messageID: Int) {
        Toast.makeText(context, messageID, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(titleID: Int, inputCallback: (value: Float) -> Unit) {
        val alertMessage = DesignUtil.formatAlertMessage(titleID)

        AlertDialog.Builder(activity)
            .setTitle(alertMessage)
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
        DELETE_THIS_DEBUGGER.text = figureList.joinToString(separator = "\n\n") { "$it" }
    }
}
