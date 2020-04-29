package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.model.status.Mode
import open.geosolve.geosolve.model.status.State
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.model.solve.type.UnknownFigure

// TODO Remove hardcoded strings, rework onTouchUp
@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var numOfCall = 0
    private var moveNode: Node? = null

    private val figure: Figure
        get() = app.figure

    fun calculateButtonClicked() {
        viewState.goToCalculationFragment()
    }

    fun markButtonClicked() {
        mode = Mode.MARK_FIND
    }

    fun editButtonClicked() {
        mode = Mode.ADD_MOVE_FIN
    }

    fun deleteButtonButton() {
        mode = Mode.DELETE
    }

    fun setValueClicked() {
        mode = Mode.SET_VAlUE
    }

    fun clearButtonClicked() {
        SolveUtil.typeSolve =
            UnknownFigure
        figure.clearFigure()
        viewState.showTypeFigure()
        viewState.updateCanvas()
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        for (node in figure.mNodes) {
            if (node.inRadius(touchX, touchY)) {
                moveNode = node
                state = State.ON_POINT
                break
            }
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        moveNode?.moveNode(touchX, touchY)
        numOfCall++
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        if (numOfCall < 3) {
            when (mode) {
                Mode.ADD_MOVE_FIN ->
                    when (state) {
                        State.ON_CANVAS -> onEditTouchCanvas(touchX, touchY)
                        State.ON_POINT -> editTouchOnPoint(touchX, touchY)
                        State.ON_LINE -> TODO()
                    }
                Mode.DELETE -> figure.delNode(touchX, touchY)
                Mode.MARK_FIND -> figure.find =
                    figure.getInRadius(touchX, touchY) { viewState.showMessage(R.string.app_name) }
                    ?: figure.find // elvis operator
                Mode.SET_VAlUE -> setValue(touchX, touchY)
            }
        }

        // crutch
        numOfCall = 0
        state = State.ON_CANVAS

        solve()
    }

    private fun solve() {
        val uiCallBack = { viewState.showTypeFigure() }
        GlobalScope.launch(Dispatchers.Main) {
            SolveUtil.solve(figure)
            uiCallBack()
        }
    }

    private fun setValue(touchX: Float, touchY: Float) {
        val element = figure.getInRadius(touchX, touchY)
        { viewState.showMessage(R.string.toast_not_set_value) }

        when (element) {
            is Line ->
                viewState.showDialog(R.string.alert_set_line) {
                    element.setValueDraw(it)
                    solve()
                }
            is Angle ->
                viewState.showDialog(R.string.alert_set_angle) {
                    element.setValueDraw(it)
                    solve()
                }
        }
    }

    //TODO(rewrite magic)
    private fun editTouchOnPoint(touchX: Float, touchY: Float) {
        for (node in figure.mNodes)
            if (node.inRadius(touchX, touchY)) {
                if (figure.mNodes.last() != node) {
                    if (node == figure.mNodes[0]) {
                        figure.addLine(
                            figure.mNodes.last(),
                            node
                        )

                        figure.addAngle(
                            figure.mNodes[figure.mNodes.size - 2],
                            figure.mNodes.last(),
                            node
                        )

                        figure.addAngle(
                            figure.mNodes.last(),
                            node,
                            figure.mNodes[1]
                        )
                    } else
                        viewState.showMessage(R.string.CRUTCH_FOR_NOW)
                }
                break
            }
    }

    //TODO(rewrite magic)
    private fun onEditTouchCanvas(touchX: Float, touchY: Float) {
        figure.addNode(Node(touchX, touchY))

        if (figure.mNodes.size > 1)
            figure.addLine(
                figure.mNodes[figure.mNodes.size - 2],
                figure.mNodes.last()
            )

        if (figure.mNodes.size > 2)
            figure.addAngle(
                figure.mNodes[figure.mNodes.size - 3],
                figure.mNodes[figure.mNodes.size - 2],
                figure.mNodes.last()
            )
    }
}