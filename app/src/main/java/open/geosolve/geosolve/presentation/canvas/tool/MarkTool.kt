package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

class MarkTool : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        /* Do nothing */
    }

    override fun onTouchNode(figure: Figure, node: Node) {
        figure.find = node.centerAngle
    }

    override fun onTouchLine(figure: Figure, line: Line) {
        figure.find = line
    }
}