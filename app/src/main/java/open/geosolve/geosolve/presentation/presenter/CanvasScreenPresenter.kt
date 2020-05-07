package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.figureList
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.DrawControl
import open.geosolve.geosolve.model.FigureController
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.solve.CallBackSolveUi
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.model.solve.type.UnknownFigure
import open.geosolve.geosolve.model.status.Mode
import open.geosolve.geosolve.model.status.State
import open.geosolve.geosolve.presentation.view.CanvasScreenView

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var moveQuantity = 0
    private var selectNode: Node? = null

    private val figure: Figure
        get() = figureList.last()
    private val figureController: FigureController = FigureController()

    fun solveButtonClicked() {
        SolveUtil.showStepSolveList(figure, object :
            CallBackSolveUi {
            override fun findNotMark() {
                viewState.showMessage(R.string.find_not_mark)
            }

            override fun solveIsNotFound() {
                viewState.showMessage(R.string.solve_not_found)
            }

            override fun userInputValue() {
                viewState.showMessage(R.string.user_input_value)
            }

            override fun solveIsFound() {
                viewState.goToSolveScreen()
            }
        })
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    fun clearButtonClicked() {
        SolveUtil.typeSolve = UnknownFigure
        SolveUtil.subTypeSolve = UnknownFigure

        figureController.removeDependent()
        if (figureList.size > 1)
            figureList.removeAt(figureList.lastIndex)
        else
            figureList[0] = Figure()

        viewState.showTypeFigure()
        viewState.updateCanvas()
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        state = State.ON_CANVAS
        DrawControl.getNode(touchX, touchY)?.let { node ->
            selectNode = node
            state = State.ON_POINT
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        selectNode?.moveNode(touchX, touchY)
        figure.mCircle?.moveDisplayRadius(touchX, touchY)
        moveQuantity++
        if (moveQuantity == 6 && selectNode == null)
            figureController.addCircle(touchX, touchY, touchX, touchY)
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        if (moveQuantity < 5) {
            when (mode) {
                Mode.ADD_MOVE_FIN ->
                    when (state) {
                        State.ON_CANVAS -> onTouchCanvas(touchX, touchY)
                        State.ON_POINT -> onTouchPoint(selectNode!!)
                        State.ON_LINE -> TODO()
                    }
                Mode.DELETE -> DrawControl.delNode(touchX, touchY)
                Mode.MARK_FIND -> DrawControl.getElement(touchX, touchY)
                { viewState.showMessage(R.string.toast_not_mark_find) }?.let {
                    find = it
                }
                Mode.SET_VALUE -> setValue(touchX, touchY)
            }
        }


        moveQuantity = 0
        selectNode = null

        setNodeChars()
        solve()

        if (figure.isComplete())
            figureList.add(Figure())
    }

    private fun solve() {
        val uiCallBack = { viewState.showTypeFigure() }
        GlobalScope.launch(Dispatchers.Main) {
            for (figureI in figureList)
                SolveUtil.solve(figureI)
            uiCallBack()
        }
    }

    private fun setNodeChars() {
        val charRange = ('A'..'Z').toList()
        for (i in 0 until figure.mNodes.size)
            figure.mNodes[i].char = charRange[i]
    }

    private fun setValue(touchX: Float, touchY: Float) {
        DrawControl.getElement(touchX, touchY)
        { viewState.showMessage(R.string.toast_not_set_value) }
            ?.let { element ->
                val message = when (element) {
                    is Line -> R.string.alert_set_line
                    is Angle -> R.string.alert_set_angle
                    else -> null
                }

                viewState.showDialog(message!!) {
                    element.setValueDraw(it)
                    solve()
                }
            }
    }

    private fun onTouchPoint(node: Node) {
        if (node != figure.mNodes.last()) { // чтобы не делать линию из точки в эту же точку
            if (node == figure.mNodes.first()) {// если фигура заканчивается в её начале
                figureController.closeFigureInStartPoint()
            } else
                viewState.showMessage(R.string.CRUTCH_FOR_NOW) // TODO(обработать случай когда фигура закнчивается не в начале)
        }
    }

    private fun onTouchCanvas(touchX: Float, touchY: Float) {
        figureController.addNode(Node(touchX, touchY))

        if (figure.mNodes.size >= 2)
            figureController.addLine(figure.mNodes[figure.mNodes.size - 2], figure.mNodes.last())

        if (figure.mLines.size >= 2)
            figureController.addAngle(figure.mLines[figure.mLines.size - 2], figure.mLines.last())

    }
}