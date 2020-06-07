package open.geosolve.geosolve.model.math

import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import kotlin.math.*

object MathUtil {

    // Distance
    fun distanceBetweenPoints(a: Node, b: Node) = hypot(a.x - b.x, a.y - b.y)
    fun distanceBetweenPoints(a: Node, x: Float, y: Float) = hypot(a.x - x, a.y - y)
    fun distanceBetweenPoints(x1: Float, y1: Float, x2: Float, y2: Float) = hypot(x1 - x2, y1 - y2)

    fun getDegree(startNode: Node, centerNode: Node, finalNode: Node): Float {
        val startVector = getVectorNode(centerNode, startNode)
        val finalVector = getVectorNode(centerNode, finalNode)

        val radianAngle =
            atan2(crossProduct(startVector, finalVector), scalarProduct(startVector, finalVector))

        return fromRadianToDegrees(radianAngle)
    }

    fun isPointInAngle(angle: Angle, x: Float, y: Float): Boolean {
        val node = Node(x, y) // TODO(Replace Node with MathPoint)
        val startAngle = getDegree(node, angle.angleNode, angle.startNode)
        val finalAngle = getDegree(node, angle.angleNode, angle.finalNode)

        return (startAngle.absoluteValue + finalAngle.absoluteValue) < 180
    }

    // formulae
    private fun fromRadianToDegrees(x: Float): Float = x * 180 / PI.toFloat()
    private fun crossProduct(a: MathPoint, b: MathPoint) = a.x * b.y - a.y * b.x
    private fun scalarProduct(a: MathPoint, b: MathPoint) = a.x * b.x + a.y * b.y

    private fun getVectorNode(a: Node, b: Node) = MathPoint(b.x - a.x, b.y - a.y)

    // Line
    fun getDistanceToLine(line: Line, x: Float, y: Float): Float {
        val distanceStart: Float = distanceBetweenPoints(line.startNode, x, y)
        val distanceFin: Float = distanceBetweenPoints(line.finalNode, x, y)
        val lineLength: Float = distanceBetweenPoints(line.startNode, line.finalNode)

        val per = (distanceStart + distanceFin + lineLength) / 2

        return sqrt(per * (per - distanceStart) * (per - distanceFin) * (per - lineLength)) / lineLength
    }

    fun isTouchOnSegment(line: Line, x: Float, y: Float): Boolean {
        val dot = { x1: Float, y1: Float,
                    x2: Float, y2: Float ->
            (x1 * x2) + (y1 * y2)
        }

        val angleLeft = dot(
            x - line.startNode.x, y - line.startNode.y,
            line.finalNode.x - line.startNode.x, line.finalNode.y - line.startNode.y
        )

        val angleRight = dot(
            x - line.finalNode.x, y - line.finalNode.y,
            line.startNode.x - line.finalNode.x, line.startNode.y - line.finalNode.y
        )

        return angleLeft >= 0 && angleRight >= 0
    }
}