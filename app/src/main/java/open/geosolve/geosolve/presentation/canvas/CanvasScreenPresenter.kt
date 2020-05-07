package open.geosolve.geosolve.presentation.canvas

import kotlinx.coroutines.launch
import moxy.InjectViewState
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.canvas.tool.EraserTool
import open.geosolve.geosolve.presentation.canvas.tool.MarkTool
import open.geosolve.geosolve.presentation.canvas.tool.PenTool
import open.geosolve.geosolve.presentation.canvas.tool.SetValueTool
import open.geosolve.geosolve.presentation.global.MvpPresenterX

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenterX<CanvasScreenView>() {

    private val tools = listOf(
        PenTool(),
        EraserTool(),
        SetValueTool({ e -> updateValue(e) }),
        MarkTool()
    )

    private var selectedTool = 0
    private var movedNode: Node? = null

    private val figure: Figure
        get() = app.figure

    init {
        check(selectedTool in tools.indices, { "Incorrect selectedTool value." })
    }

    fun isUsedByContent(x: Float, y: Float): Boolean {
        figure.mNodes.forEach { node ->
            if (node.inRadius(x, y)) return true
        }

        return false
    }

    fun onMoveStart(x: Float, y: Float) {
        figure.mNodes.forEach { node ->
            if (node.inRadius(x, y)) {
                movedNode = node
                return
            }
        }

        throw RuntimeException("Node not found, but isUsed work's")
    }

    fun onMove(x: Float, y: Float) {
        movedNode?.moveNode(x, y)
    }

    fun onMoveFinished(x: Float, y: Float) {
        movedNode = null
    }

    fun onTouch(x: Float, y: Float) {

        val nodeOnTouch = figure.findNode(x, y)

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

        figure.mNodes.forEach { node ->
            node.char = charRange.nextChar()
        }
    }

    private fun solve() = launch {
        SolveUtil.solve(figure)

        viewState.showFigureType()
        viewState.updateCanvas()
    }
}