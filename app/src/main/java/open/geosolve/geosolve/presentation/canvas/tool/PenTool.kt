package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.helper.FigureManipulator

class PenTool : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        if (figure.isClose) return

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
        if (figure.isClose) return
        closeFigure(figure)
    }

    override fun onTouchLine(figure: Figure, line: Line) {
        if (figure.isClose) return
        closeFigure(figure)
    }

    // FIXME(CHECK) Проверить корректность алгоритма
    private fun closeFigure(figure: Figure) {
        val node = figure.nodes.first()

        FigureManipulator.addLine(figure.nodes.last(), node)
        FigureManipulator.addAngle(node.startLine?.startNode?.startLine!!, node.startLine!!)
        FigureManipulator.addAngle(node.startLine!!, node.finalLine!!)
    }
}