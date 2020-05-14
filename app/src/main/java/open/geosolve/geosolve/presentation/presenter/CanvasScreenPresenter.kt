package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.delElement
import open.geosolve.geosolve.App.Companion.figureList
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.DrawControl
import open.geosolve.geosolve.model.FigureController
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.data.generalized.Bind
import open.geosolve.geosolve.model.data.generalized.Movable
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.CallBackSolveUi
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.model.status.Mode
import open.geosolve.geosolve.model.status.State
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.view.screens.solveScreen.RecycleAdapter

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var moveQuantity = 0
    private var selectMovable: Movable? = null
    private var lastNode: Node? = null

    private val figure: Figure
        get() = figureList.last()

    fun solveButtonClicked() {
        SolveUtil.showStepSolveList(object : CallBackSolveUi {
            override fun findNotMark() {
                viewState.showMessage(R.string.find_not_mark)
            }

            override fun solveIsNotFound() {
                viewState.showMessage(R.string.solve_not_found)
            }

            override fun userInputValue() {
                viewState.showMessage(R.string.user_input_value)
            }

            override fun solveIsFound(list: List<SolveGraph>) {
                RecycleAdapter.addAll(list)
                viewState.goToSolveScreen()
            }
        })
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    fun clearButtonClicked() {
        if (figureList.size > 1 && figure.isEmpty()) {
            FigureController.removeDependent()
            figureList.remove(figure)
        }
        if (figureList.isNotEmpty()) {
            FigureController.removeDependent()
            figureList.remove(figure)
        }
        figureList.add(Figure())

        lastNode = null // TODO(rewrite this)

        viewState.showTypeFigure()
        viewState.updateCanvas()
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        state = State.ON_CANVAS

        DrawControl.getMovable(touchX, touchY)?.let { movable ->
            selectMovable = movable
            when (movable) {
                is Node -> state = State.ON_POINT
                is Circle -> state = State.ON_CIRCLE
            }
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        selectMovable?.move(touchX, touchY)
        figure.mCircle?.move(touchX, touchY) // rewrite

        moveQuantity++
        if (moveQuantity == 6 && selectMovable == null) {
            if (!figure.isEmpty()) figureList.add(Figure()) // переходим на следующую фигуру
            FigureController.addCircle(touchX, touchY)
        }
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        if (moveQuantity < 5) {
            when (mode) {
                Mode.ADD_MOVE_FIN ->
                    when (state) {
                        State.ON_CANVAS -> onTouchCanvas(touchX, touchY)
                        State.ON_POINT -> onTouchPoint(selectMovable!! as Node)
                        State.ON_LINE -> TODO("onTouchLine()")
                        State.ON_CIRCLE -> onTouchCircleLine(selectMovable!! as Circle, touchX, touchY)
                    }
                Mode.DELETE -> {
                    DrawControl.delNode(touchX, touchY); lastNode = null
                }
                Mode.MARK_FIND -> DrawControl.getGraphElement(touchX, touchY)?.let { find = it }
                Mode.SET_VALUE -> setValue(touchX, touchY)
            }
        }

        moveQuantity = 0
        selectMovable = null

        setNodeChars()
        solve()

        if (figure.isClose())
            lastNode = null

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

    private fun setValue(touchX: Float, touchY: Float) {
        DrawControl.getGraphElement(touchX, touchY)?.let { element ->
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

    private fun onTouchPoint(touchNode: Node) { // TODO(rewrite this shit code)
        if (lastNode != null) {
            if (touchNode != if (figure.mNodes.isNotEmpty()) figure.mNodes.last() else touchNode) { // чтобы не делать линию из точки в эту же точку

                if (!figure.mNodes.contains(touchNode))
                    figure.mNodes.add(touchNode)

                val newLine = Line(lastNode!!, touchNode)
                FigureController.addLine(newLine)

                updateAngles() // FIXME(updateAngles)
            }
        }
        lastNode = touchNode
    }

    private fun updateAngles() { // FIXME(updateAngles)
        figure.mAngles.clear()

        for (startLine in figure.mLines) // FIXME(updateAngles)
            for (finalLine in figure.mLines)
                if (finalLine != startLine && startLine.finalNode == finalLine.startNode)
                    FigureController.addAngle(Angle(startLine, finalLine))
    }

    private fun onTouch(touchX: Float, touchY: Float, bind: Bind? = null) {
        val newNode = Node(touchX, touchY)
        newNode.bind = bind

        FigureController.addNode(newNode)

        lastNode?.let {
            val newLine = Line(lastNode!!, newNode)
            FigureController.addLine(newLine)

            updateAngles() // FIXME(updateAngles)
        }

        lastNode = newNode
    }

    private fun onTouchLine(line: Line, x: Float, y: Float) {
        onTouch(x, y, line)
    }

    private fun onTouchCircleLine(circle: Circle, x: Float, y: Float) {
        onTouch(x, y, circle)
    }

    private fun onTouchCanvas(touchX: Float, touchY: Float) {
        onTouch(touchX, touchY)
    }

    private fun setNodeChars() {
        val charRange = ('A'..'Z').toList()
        for (i in App.allNodes.indices)
            App.allNodes[i].char = charRange[i]
    }
}