package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.helper.FigureManipulator

class EraserTool : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        /* Do nothing */
    }

    override fun onTouchNode(figure: Figure, node: Node) {
        FigureManipulator.deleteNode(node)
    }

    override fun onTouchLine(figure: Figure, line: Line) {
        /* FIXME Unimplemented. Remove line and (suspend) node */
    }
}