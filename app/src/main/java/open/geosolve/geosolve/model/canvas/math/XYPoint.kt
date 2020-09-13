package open.geosolve.geosolve.model.canvas.math

import open.geosolve.geosolve.model.canvas.data.elements.Node

class XYPoint(override val x: Float, override val y: Float) : XY {
    fun toNode() = Node(x, y)
}