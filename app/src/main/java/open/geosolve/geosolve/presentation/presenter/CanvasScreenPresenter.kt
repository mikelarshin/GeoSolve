package open.geosolve.geosolve.presentation.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.GlobalFiguresController.allAngles
import open.geosolve.geosolve.GlobalFiguresController.allLines
import open.geosolve.geosolve.GlobalFiguresController.figureList
import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.ElementGetter.getGraphElement
import open.geosolve.geosolve.model.ElementGetter.getMovable
import open.geosolve.geosolve.model.EventControl
import open.geosolve.geosolve.model.EventControl.setNodeChars
import open.geosolve.geosolve.model.EventControl.solve
import open.geosolve.geosolve.model.FigureController
import open.geosolve.geosolve.model.FigureController.figure
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

    private var mode = Mode.ADD
    private var state = State.ON_CANVAS
    private var moveQuantity = 0
    private var selectMovable: Movable? = null
    private var lastNode: Node? = null

    fun showSolveClick() {
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
            FigureController.updateFind()
            figureList.remove(figure)
        }
        if (figureList.isNotEmpty()) {
            FigureController.updateFind()
            figureList.remove(figure)
        }
        figureList.add(Figure())

        lastNode = null // TODO(rewrite this)

        viewState.showTypeFigure()
        viewState.updateCanvas()
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        state = State.ON_CANVAS

        getMovable(touchX, touchY)?.let { movable ->
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
            if (!figure.isEmpty())
                figureList.add(Figure()) // переходим на следующую фигуру
            FigureController.addCircle(touchX, touchY)
            onTouchPoint(figure.mCircle!!.centerNode)
        }
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        if (moveQuantity < 5) {
            when (mode) {
                Mode.ADD ->
                    when (state) {
                        State.ON_CANVAS -> onTouchCanvas(touchX, touchY)
                        State.ON_POINT -> onTouchPoint(selectMovable!! as Node)
                        State.ON_LINE -> TODO("onTouchLine()")
                        State.ON_CIRCLE -> onTouchCircleLine(
                            selectMovable!! as Circle,
                            touchX,
                            touchY
                        )
                    }
                Mode.DELETE -> {
                    EventControl.delete(touchX, touchY);
                    lastNode = null
                }
                Mode.MARK_FIND -> getGraphElement(touchX, touchY)?.let { find = it }
                Mode.SET_VALUE -> setValue(touchX, touchY)
            }
        }

        moveQuantity = 0
        selectMovable = null

        setNodeChars()
        solveAndCallBack()

        if (figure.isClose())
            lastNode = null

        if (figure.isComplete())
            figureList.add(Figure()) // переход на следующую фигуру
    }

    private fun solveAndCallBack() { // TODO(нужно чтобы эта функция не выполнялась когда мы двигаем точки)
        solve(
            solveUiCallBack = {
                viewState.showTypeFigure()
                viewState.updateCanvas()
            })
    }

    private fun onTouchPoint(touchNode: Node) {
        if (touchNode != lastNode && lastNode != null) {  // если стартовая и конечная точка линии не равны и прошлая точка есть

            val newLine = Line(lastNode!!, touchNode)
            if (!equalsContainsLine(newLine)) {
                FigureController.addLine(newLine)

                if (!figure.mNodes.contains(lastNode!!))
                    figure.mNodes.add(lastNode!!)
                if (!figure.mNodes.contains(touchNode))
                    figure.mNodes.add(touchNode)

                updateLines()
                updateAngles() // FIXME(updateAngles)
            }
        }
        lastNode = touchNode
    }

    private fun onTouch(touchX: Float, touchY: Float, bind: Bind? = null) {
        val newNode = Node(touchX, touchY)
        newNode.bind = bind

        FigureController.addNode(newNode)

        lastNode?.let {
            val newLine = Line(lastNode!!, newNode)
            FigureController.addLine(newLine)

            if (!figure.mNodes.contains(lastNode!!))
                figure.mNodes.add(lastNode!!)

            updateLines()
            updateAngles() // FIXME(updateAngles)
        }

        lastNode = newNode
    }

    private fun onTouchCanvas(touchX: Float, touchY: Float) {
        onTouch(touchX, touchY)
    }

    private fun onTouchLine(line: Line, x: Float, y: Float) {
        onTouch(x, y, line)
    }

    private fun onTouchCircleLine(circle: Circle, x: Float, y: Float) {
        onTouch(x, y, circle)
    }

    private fun updateLines() {
        val lineList = allLines - figure.mLines
        for (line in lineList)
            if (figure.mNodes.contains(line.startNode) && figure.mNodes.contains(line.finalNode))
                FigureController.addLine(line)
    }

    private fun updateAngles() {
        for (startLine in figure.mLines)
            for (finalLine in figure.mLines)
                if (finalLine != startLine && startLine.finalNode == finalLine.startNode) {
                    val newAngle = Angle(startLine, finalLine)
                    if (!equalsContainsAngle(newAngle))
                        FigureController.addAngle(newAngle)
                }

        // Простой перебор по всем вариантам углов которые есть в фигуре
        // Это система работает не всегда и в будующем принесёт много бед
        // Нужно её переписать FIXME(updateAngles)
    }

    private fun equalsContainsAngle(newAngle: Angle): Boolean {
        for (angle in allAngles)
            if (angle.startNode == newAngle.startNode &&
                angle.finalNode == newAngle.finalNode &&
                angle.angleNode == newAngle.angleNode
            )
                return true
        return false
    }

    private fun equalsContainsLine(newLine: Line): Boolean {
        for (line in allLines)
            if ((line.startNode == newLine.startNode && line.finalNode == newLine.finalNode) ||
                (line.startNode == newLine.finalNode && line.finalNode == newLine.startNode)
            )
                return true
        return false
    }

    private fun setValue(touchX: Float, touchY: Float) {
        getGraphElement(touchX, touchY)?.let { element ->
            val message = when (element) {
                is Line -> R.string.alert_set_line
                is Angle -> R.string.alert_set_angle
                is Circle -> R.string.alert_set_circle
                else -> null!!
            }

            viewState.showDialog(message) {
                element.setValueDraw(it)
                solveAndCallBack()
            }
        }
    }
}