package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

class SetValueTool(
    private val setValue: ((element: Element) -> Unit)
) : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        /* Do nothing */
    }

    override fun onTouchNode(figure: Figure, node: Node) {
        setValue(node.centerAngle!!)
    }

    override fun onTouchLine(figure: Figure, line: Line) {
        setValue(line)
    }
}