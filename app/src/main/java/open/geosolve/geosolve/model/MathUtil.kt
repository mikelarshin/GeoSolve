package open.geosolve.geosolve.model

import open.geosolve.geosolve.model.data.Node
import kotlin.math.hypot

object MathUtil {
    fun distanceBetweenPoints(node1: Node, node2: Node)
            = hypot(node1.x - node2.x, node1.y - node2.y)
    fun distanceBetweenPoints(node1: Node, x: Float, y: Float)
            = hypot(node1.x - x, node1.y - y)
    fun distanceBetweenPoints(x1: Float, y1: Float, x2: Float, y2: Float)
            = hypot(x1 - x2, y1 - y2)
}