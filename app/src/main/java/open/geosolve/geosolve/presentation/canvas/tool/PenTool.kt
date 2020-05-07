package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.helper.FigureManipulator

class PenTool : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        if (figure.figureClosed) return

        FigureManipulator.addNode(Node(x, y))

        if (figure.nodes.size > 1) {
            FigureManipulator.addLine(

                figure.nodes[figure.nodes.size - 2],
                figure.nodes.last()
            )
        }

        if (figure.lines.size > 1) {
            FigureManipulator.addAngle(

                figure.lines[figure.lines.size - 2],
                figure.lines.last()
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
        val node = figure.nodes.last()

        FigureManipulator.addLine(figure.nodes.last(), figure.nodes.first())
        FigureManipulator.addAngle(node.startLine?.startNode?.startLine!!, node.startLine!!)
        FigureManipulator.addAngle(node.startLine!!, node.finalLine!!)

        figure.figureClosed = true
    }
}