package open.geosolve.geosolve.view.screens.canvasScreen

import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
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
            presenter.calculateButtonClicked()
        }

        layout.mark_mode_button.setOnClickListener {
            presenter.markButtonClicked()
        }

        layout.edit_mode_button.setOnClickListener {
            presenter.editButtonClicked()
        }

        layout.delete_mode_button.setOnClickListener {
            presenter.deleteButtonButton()
        }

        layout.set_value_mode_button.setOnClickListener {
            presenter.setValueClicked()
        }

        layout.clear_button.setOnClickListener {
            presenter.clearButtonClicked()
        }
    }

    override fun updateCanvas() {
        layout.canvas.invalidate()
    }

    override fun goToCalculationFragment() {
        findNavController().navigate(R.id.action_to_solve)
    }

    override fun showDialog(title: String, okCallback: (value: Float) -> Unit) {
        AlertDialog.Builder(activity)
            .setTitle(title)
            .setView(
                LayoutInflater.from(activity).inflate(R.layout.dialog_input_value, null)
            )
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                if (!(dialog as AlertDialog).input.text.isNullOrBlank())
                    okCallback(dialog.input.text.toString().toFloat())
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun showTypeFigure() {
        text_type_figure.text =
            if (SolveUtil.typeSolve::class.simpleName != "UnknownFigure")
                SolveUtil.typeSolve::class.simpleName
            else
                ""
    }

    override fun makeMessageForUsers(message: String) {
        Toast.makeText(context,"На этой точке нет угла", Toast.LENGTH_SHORT).show()
    }

}
