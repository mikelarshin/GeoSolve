package open.geosolve.geosolve.presentation.canvas

import kotlinx.coroutines.launch
import moxy.InjectViewState
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.global.MvpPresenterX

@InjectViewState
class CanvasScreenPresenter(val app: App) : MvpPresenterX<CanvasScreenView>() {

    private var figureClosed = false
    private var movedNode: Node? = null

    private val figure: Figure
        get() = app.figure

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

        val isCanvasTouch = !isNode(x, y)

        if (!figureClosed) {
            if (isCanvasTouch)
                touchCanvas(x, y)
            else
                touchPoint(x, y)
        }

        updateNodesNames()
        solve()
    }

    private fun isNode(x: Float, y: Float): Boolean {
        figure.mNodes.forEach { node ->
            if (node.inRadius(x, y)) {
                return true
            }
        }

        return false
    }

    private fun touchCanvas(x: Float, y: Float) {

        figure.addNode(Node(x, y))

        if (figure.mNodes.size > 1) {
            figure.addLine(
                figure.mNodes[figure.mNodes.size - 2],
                figure.mNodes.last()
            )
        }

        if (figure.mLines.size > 1) {
            figure.addAngle(
                figure.mLines[figure.mLines.size - 2],
                figure.mLines.last()
            )
        }
    }

    private fun touchPoint(x: Float, y: Float) {
        for (node in figure.mNodes) {
            if (node.inRadius(x, y)) {
                if (figure.mNodes.last() == node) continue

                if (node == figure.mNodes.first()) {
                    figure.closeFigure = true
                    figure.addLine(figure.mNodes.last(), node)

                    figure.addAngle(node.startLine?.startNode?.startLine!!, node.startLine!!)

                    figure.addAngle(node.startLine!!, node.finalLine!!)

                } else {
                    viewState.showMessage(R.string.CRUTCH_FOR_NOW)
                }

                break
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

        viewState.showTypeFigure()
    }
}