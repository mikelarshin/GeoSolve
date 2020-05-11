package open.geosolve.geosolve.presentation.presenter

import android.util.Log
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
import open.geosolve.geosolve.view.screens.solveScreen.RecycleAdapter
import java.util.*

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var moveQuantity = 0
    private var selectMovable: Movable? = null
    private var lastNode: Node? = null
    private var lastLine: Line? = null

    private val figure: Figure
        get() = figureList.last()

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

            override fun solveIsFound(list: List<Element>) {
                RecycleAdapter.addAll(list)
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

        FigureController.removeDependent()
        if (figureList.size > 1)
            figureList.removeAt(figureList.lastIndex)
        else
            figureList[0] = Figure()

        lastNode = null
        lastLine = null

        viewState.showTypeFigure()
        viewState.updateCanvas()
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        state = State.ON_CANVAS

        DrawControl.getMovable(touchX, touchY)?.let { movable ->
            selectMovable = movable
            when(movable) {
                is Node -> state = State.ON_POINT
                is Circle -> state = State.ON_CIRCLE
            }
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        selectMovable?.move(touchX, touchY)
        figure.mCircle?.move(touchX, touchY) // rewrite

        moveQuantity++
        if (moveQuantity == 6 && selectMovable == null)
            FigureController.addCircle(touchX, touchY)
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        if (moveQuantity < 5) {
            when (mode) {
                Mode.ADD_MOVE_FIN ->
                    when (state) {
                        State.ON_CANVAS -> onTouchCanvas(touchX, touchY)
                        State.ON_POINT -> onTouchPoint(selectMovable!! as Node) // TODO(Rewrite)
                        State.ON_LINE -> TODO() // onTouchLine()
                        State.ON_CIRCLE -> onTouchCircleLine(selectMovable!! as Circle, touchX, touchY)
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
        selectMovable = null

        setNodeChars()
        solve()

        if (figure.isComplete())
            figureList.add(Figure()) // переход на следующую фигуру
    }

    private fun solve() {
        val uiCallBack = {
            viewState.showTypeFigure()
            viewState.updateCanvas()
        }
        GlobalScope.launch(Dispatchers.Main) {
            SolveUtil.solve(figureList)
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
                    else -> null!!
                }

                viewState.showDialog(message) {
                    element.setValueDraw(it)
                    solve()
                }
            }
    }

    private fun onTouchPoint(node: Node) {
        if (node != if (figure.mNodes.isNotEmpty()) figure.mNodes.last() else node) { // чтобы не делать линию из точки в эту же точку
            if (node == figure.mNodes.first()) { // если фигура заканчивается в её начале TODO(обработать другие случаи)

                val closeNode = figure.mNodes.first()

                FigureController.addLine(Line(lastNode!!, closeNode))

                FigureController.addAngle(Angle(closeNode.startLine?.startNode?.startLine!!, closeNode.startLine!!))
                FigureController.addAngle(Angle(closeNode.startLine!!, closeNode.finalLine!!))

            } else
                viewState.showMessage(R.string.CRUTCH_FOR_NOW) // TODO(обработать случай когда фигура закнчивается не в начале)
        }
        lastNode = node
    }

    private fun onTouchLine(line: Line, x: Float, y: Float) {
        onTouchCanvas(x, y, line)
    }

    private fun onTouchCircleLine(circle: Circle, x: Float, y: Float) {
        onTouchCanvas(x, y, circle)
    }

    private fun onTouchCanvas(touchX: Float, touchY: Float, bind: Bind? = null) {
        val newNode = Node(touchX, touchY)
        newNode.bind = bind

        FigureController.addNode(newNode)

        lastNode?.let {
            val newLine = Line(lastNode!!, newNode)
            FigureController.addLine(newLine)

            lastLine?.let {
                val newAngle = Angle(lastLine!!, newLine)
                FigureController.addAngle(newAngle)
            }

            lastLine = newLine
        }

        lastNode = newNode
    }
}