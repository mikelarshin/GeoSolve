package open.geosolve.geosolve.presentation.canvas

import moxy.InjectViewState
import open.geosolve.geosolve.App
import open.geosolve.geosolve.presentation.global.MvpPresenterX

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenterX<CanvasScreenView>() {

    fun isUsedByContent(x: Float, y: Float): Boolean {
        return true
    }

    fun onMoveStart(x: Float, y: Float) {}

    fun onMove(x: Float, y: Float) {}

    fun onMoveFinished(x: Float, y: Float) {}

    fun onTouch(x: Float, y: Float) {}
}