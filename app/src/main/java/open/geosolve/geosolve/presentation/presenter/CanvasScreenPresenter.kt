package open.geosolve.geosolve.presentation.presenter

import android.util.Log
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.repository.enum.Mode
import open.geosolve.geosolve.repository.enum.State
import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.model.Node

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var numOfCall = 0

    private val figure: Figure
        get() = app.figure

    fun onTouchDown(touchX: Float, touchY: Float) {
        Log.d("GeoSolve", "onTouchDown")

        for (node in figure.mNodes) {
            if (node.inRadius(touchX, touchY)) {
                node.isMove = true
                state = State.ON_POINT
                break
            }
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        Log.d("GeoSolve", "onTouchMove")

        for (node in figure.mNodes) {
            if (node.isMove) {
                node.moveNode(touchX, touchY)
            }
        }
        numOfCall++
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        Log.d("GeoSolve", "onTouchUp")

        when (mode) {
            Mode.ADD_MOVE_FIN -> {
                when (state) {
                    State.ON_CANVAS -> {
                        figure.addNode(Node(touchX, touchY))
                        if (figure.mNodes.size > 1)
                            figure.addLine(
                                figure.mNodes[figure.mNodes.size - 2],
                                figure.mNodes.last()
                            )
                    }
                    State.ON_POINT -> if (numOfCall < 2)
                        for (node in figure.mNodes)
                            if (node.inRadius(touchX, touchY)) {
                                figure.addLine(figure.mNodes.last(), node)
                                break
                            }
                }
            }
            Mode.DEL_MOVE -> figure.delNode(touchX, touchY)
            Mode.MARK_FIND -> figure.find = figure.getInRadius(touchX, touchY) ?: figure.find
            Mode.SET_VAlUE -> {
                // TODO Set value for element
                figure.getInRadius(touchX, touchY)
            }
        }

        numOfCall = 0
        state = State.ON_CANVAS
        figure.stopAllNode()
    }
}