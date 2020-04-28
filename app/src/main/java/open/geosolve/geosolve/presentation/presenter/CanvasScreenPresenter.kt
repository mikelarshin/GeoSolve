package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.repository.UnwindCallback
import open.geosolve.geosolve.repository.enum.Mode
import open.geosolve.geosolve.repository.enum.State
import open.geosolve.geosolve.repository.model.Angle
import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.model.Line
import open.geosolve.geosolve.repository.model.Node
import open.geosolve.geosolve.repository.solve.SolveUtil
import open.geosolve.geosolve.repository.solve.type.UnknownFigure

// TODO Remove hardcoded strings, rework onTouchUp
@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var numOfCall = 0

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
        mode = Mode.DEL_MOVE
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
                node.isMove = true
                state = State.ON_POINT_OR_LINE
                break
            }
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        for (node in figure.mNodes) {
            if (node.isMove) {
                node.moveNode(touchX, touchY)
            }
        }
        numOfCall++
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        when (mode) {
            Mode.ADD_MOVE_FIN ->
                when (state) {
                    State.ON_CANVAS -> onTouchCanvas(touchX, touchY)
                    State.ON_POINT_OR_LINE ->
                        if (numOfCall < 2)
                            touchOnPointOrLine(touchX, touchY)
                }
            Mode.DEL_MOVE -> figure.delNode(touchX, touchY)
            Mode.MARK_FIND -> figure.find = figure.getInRadius(touchX, touchY) ?: figure.find
            Mode.SET_VAlUE -> {
                if (numOfCall < 2) {
                    setValue(touchX, touchY)
                }
            }
        }

        numOfCall = 0
        state = State.ON_CANVAS
        figure.stopAllNode()

        showTypeCallback()

        // CRUNCH
        SolveUtil.unwindTree(figure, object : UnwindCallback {

            override fun emptyElement() {

            }

            override fun emptyStackCallback() {

            }
        })
    }

    private fun showTypeCallback() {
        val onUIlambda = { viewState.showTypeFigure() }
        GlobalScope.launch(Dispatchers.Main) {
            SolveUtil.solve(figure)
            onUIlambda()
        }
    }

    private fun setValue(touchX: Float, touchY: Float) {
        when (val element = figure.getInRadius(touchX, touchY)) {

            is Line ->
                viewState.showDialog("Введите длину линии") {
                    element.setValueDraw(it)
                    showTypeCallback()
                }

            is Angle ->
                viewState.showDialog("Введите значение угла") {
                    element.setValueDraw(it)
                    showTypeCallback()
                }
        }
    }

    private fun touchOnPointOrLine(touchX: Float, touchY: Float) {
        for (node in figure.mNodes)
            if (node.inRadius(touchX, touchY)) {
                if (figure.mNodes.last() != node) {
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
                    ) //TODO(При соедниении с точкой на линии добавляется 3 угла)
                }

                //TODO(При нажатии на линию появляется точка на линии)
                return
            }
    }

    fun onTouchCanvas(touchX: Float, touchY: Float) {
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