package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

class PenTool : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        if (figure.figureClosed) return

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

    override fun onTouchNode(figure: Figure, node: Node) {
        if (figure.figureClosed) return
        closeFigure(figure)
    }

    override fun onTouchLine(figure: Figure, line: Line) {
        if (figure.figureClosed) return
        closeFigure(figure)
    }

    // FIXME Is this algorithm correctly?
    private fun closeFigure(figure: Figure) {
        val node = figure.mNodes.last()

        figure.addLine(figure.mNodes.last(), figure.mNodes.first())
        figure.addAngle(node.startLine?.startNode?.startLine!!, node.startLine!!)
        figure.addAngle(node.startLine!!, node.finalLine!!)

        figure.figureClosed = true
    }
}