package open.geosolve.geosolve.ui.canvas

import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.canvas.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.canvas.CanvasScreenView
import open.geosolve.geosolve.ui.global.MvpFragmentX
import open.v0gdump.field.InteractiveFieldCallback


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas),
    CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter(app) }

    override fun setupLayout() {
        layout.field.attach(app.figure)
        layout.field.callback = object : InteractiveFieldCallback {

            override fun isUsedByContent(x: Float, y: Float): Boolean {
                return presenter.isUsedByContent(x, y)
            }

            override fun onMove(x: Float, y: Float) {
                presenter.onMove(x, y)
            }

            override fun onMoveFinished(x: Float, y: Float) {
                presenter.onMoveFinished(x, y)
            }

            override fun onMoveStart(x: Float, y: Float) {
                presenter.onMoveStart(x, y)
            }

            override fun onTouch(x: Float, y: Float) {
                presenter.onTouch(x, y)
            }

        }
    }

    override fun goToSolveScreen() {
        findNavController().navigate(R.id.action_to_solve)
    }

    override fun updateCanvas() {
        field.invalidate()
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

        text_figure_type.text = if (subTypeFigure?.isNotEmpty() == true)
            "$typeFigure : $subTypeFigure"
        else
            typeFigure
    }

    override fun showMessage(messageID: Int) {
        Toast.makeText(context, messageID, Toast.LENGTH_SHORT).show()
    }
}
