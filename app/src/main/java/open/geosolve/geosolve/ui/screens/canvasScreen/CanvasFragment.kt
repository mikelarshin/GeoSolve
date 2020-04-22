package open.geosolve.geosolve.ui.screens.canvasScreen

import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.ui.MvpFragmentX


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter(app) }

    override fun setupLayout() {

        layout.canvas.attachFigure(app.figure)
        layout.canvas.onTouchUp = { x, y -> presenter.onTouchUp(x, y) }
        layout.canvas.onTouchDown = { x, y -> presenter.onTouchDown(x, y) }
        layout.canvas.onTouchMove = { x, y -> presenter.onTouchMove(x, y) }

        layout.calc_button.setOnClickListener {
            TODO("Unimplemented")
        }

        layout.mark_mode_button.setOnClickListener {
            TODO("Unimplemented")
        }

        layout.edit_mode_button.setOnClickListener {
            TODO("Unimplemented")
        }

        layout.delete_mode_button.setOnClickListener {
            TODO("Unimplemented")
        }

        layout.set_value_mode_button.setOnClickListener {
            TODO("Unimplemented")
        }

        layout.clear_button.setOnClickListener {
            TODO("Unimplemented")
        }
    }
}
