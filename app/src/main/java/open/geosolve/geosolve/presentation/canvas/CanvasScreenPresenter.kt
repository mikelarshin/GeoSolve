package open.geosolve.geosolve.presentation.canvas

import kotlinx.coroutines.launch
import moxy.InjectViewState
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.helper.FigureManipulator
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.canvas.tool.EraserTool
import open.geosolve.geosolve.presentation.canvas.tool.MarkTool
import open.geosolve.geosolve.presentation.canvas.tool.PenTool
import open.geosolve.geosolve.presentation.canvas.tool.SetValueTool
import open.geosolve.geosolve.presentation.global.MvpPresenterX

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenterX<CanvasScreenView>() {

    //region Инструменты

    var selectedTool = 0
    private val tools = listOf(
        PenTool(),
        EraserTool(),
        SetValueTool({ e -> updateValue(e) }),
        MarkTool()
    )

    //endregion

    private val figure: Figure
        get() = app.figure

    private var movedNode: Node? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showFigureType()
    }

    fun isUsedByContent(x: Float, y: Float): Boolean {
        figure.nodes.forEach { node ->
            if (node.inRadius(x, y)) {
                return true
            }
        }

        return false
    }

    fun onMoveStart(x: Float, y: Float) {
        figure.nodes.forEach { node ->
            if (node.inRadius(x, y)) {
                movedNode = node
                return
            }
        }

        throw RuntimeException("Точка не найдена, но isUsedByContent сработал")
    }

    fun onMove(x: Float, y: Float) {
        movedNode?.moveNode(x, y)
    }

    fun onMoveFinished(x: Float, y: Float) {
        movedNode = null
    }

    fun onTouch(x: Float, y: Float) {

        val nodeOnTouch = FigureManipulator.findNode(x, y)

        if (nodeOnTouch == null) {
            tools[selectedTool].onTouchCanvas(figure, x, y)
        } else {
            tools[selectedTool].onTouchNode(figure, nodeOnTouch)
        }

        updateNodesNames()
        solve()
    }

    private fun updateValue(element: Element) {
        when (element) {
            is Line -> {
                viewState.showInputDialog(R.string.alert_set_line) {
                    element.setValueDraw(it)
                    solve()
                }
            }
            is Angle -> {
                viewState.showInputDialog(R.string.alert_set_angle) {
                    element.setValueDraw(it)
                    solve()
                }
            }
        }
    }

    private fun updateNodesNames() {
        val charRange = ('A'..'Z').iterator()

        figure.nodes.forEach { node ->
            node.char = charRange.nextChar()
        }
    }

    private fun solve() = launch {
        SolveUtil.solve(figure)

        viewState.showFigureType()
        viewState.updateCanvas()
    }

    fun clearFigure() {
        figure.clear()

        viewState.showFigureType()
        viewState.updateCanvas()
    }
}