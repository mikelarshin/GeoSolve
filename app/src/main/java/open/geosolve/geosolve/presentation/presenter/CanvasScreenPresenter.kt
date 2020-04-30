package open.geosolve.geosolve.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.CallBackSolveUi
import open.geosolve.geosolve.presentation.view.CanvasScreenView
import open.geosolve.geosolve.model.status.Mode
import open.geosolve.geosolve.model.status.State
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.model.solve.type.UnknownFigure
import open.geosolve.geosolve.view.screens.solveScreen.RecycleAdapter

// TODO(maybe divided onClickButton method and touch cycle???)
// TODO rework onTouchUp
@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenter<CanvasScreenView>() {

    private var mode = Mode.ADD_MOVE_FIN
    private var state = State.ON_CANVAS
    private var numOfCall = 0
    private var moveNode: Node? = null

    private val figure: Figure
        get() = app.figure

    fun solveButtonClicked(){
        SolveUtil.showStepSolveList(figure, object : CallBackSolveUi {
            override fun findNotMark() {
                viewState.showMessage(R.string.find_not_mark)
            }

            override fun solveIsNotFound() {
                viewState.showMessage(R.string.solve_not_found)
            }

            override fun solveIsFound() {
                viewState.goToSolveScreen()
            }
        })
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
        SolveUtil.typeSolve = UnknownFigure
        figure.clearFigure()
        viewState.showTypeFigure()
        viewState.updateCanvas()
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        state = State.ON_CANVAS
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
        viewState.showTypeFigure()
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
                    figure.getInRadius(touchX, touchY) { viewState.showMessage(R.string.toast_not_mark_find) }
                    ?: figure.find
                Mode.SET_VAlUE -> setValue(touchX, touchY)
            }
        }

        // crutch
        numOfCall = 0
        moveNode = null

        setChars()
        solve()
    }

    private fun solve() {
        val uiCallBack = { viewState.showTypeFigure() }
        GlobalScope.launch(Dispatchers.Main) {
            SolveUtil.solve(figure)
            uiCallBack()
        }
    }

    private fun setChars(){
        val charRange = ('A'..'Z').toList()
        for (i in 0 until figure.mNodes.size)
            figure.mNodes[i].char = charRange[i]
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
                        figure.addLine(figure.mNodes.last(), node)

                        figure.addAngle(node.startLine?.startNode?.startLine!!, node.startLine!!)

                        figure.addAngle(node.startLine!!, node.finalLine!!)

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

        if (figure.mLines.size > 1)
            figure.addAngle(
                figure.mLines[figure.mLines.size - 2],
                figure.mLines.last()
            )
    }
}