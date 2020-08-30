package open.geosolve.geosolve.model.canvas.math

import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.Node
import kotlin.math.*


object MathUtil {

    // Distance
    fun distanceBetweenPoints(a: Node, b: Node) = hypot(a.x - b.x, a.y - b.y)
    fun distanceBetweenPoints(a: Node, x: Float, y: Float) = hypot(a.x - x, a.y - y)
    fun distanceBetweenPoints(x1: Float, y1: Float, x2: Float, y2: Float) = hypot(x1 - x2, y1 - y2)

    fun getDegree(startNode: Node, centerNode: Node, finalNode: Node): Float {
        val startVector = getVectorPoint(centerNode, startNode)
        val finalVector = getVectorPoint(centerNode, finalNode)

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

    // formulas
    private fun fromRadianToDegrees(x: Float): Float = x * 180 / PI.toFloat()
    private fun crossProduct(a: MathPoint, b: MathPoint) = a.x * b.y - a.y * b.x
    private fun scalarProduct(a: MathPoint, b: MathPoint) = a.x * b.x + a.y * b.y // this is dot()

    private fun getVectorPoint(a: Node, b: Node) = MathPoint(b.x - a.x, b.y - a.y)

    // Line
    fun getDistanceToLine(line: Line, x: Float, y: Float): Float {
        val distanceStart: Float = distanceBetweenPoints(line.firstNode, x, y)
        val distanceFin: Float = distanceBetweenPoints(line.secondNode, x, y)
        val lineLength: Float = distanceBetweenPoints(line.firstNode, line.secondNode)

        val per = (distanceStart + distanceFin + lineLength) / 2

        return sqrt(per * (per - distanceStart) * (per - distanceFin) * (per - lineLength)) / lineLength
    }

    fun getPointProjectToLine(line: Line, x: Float, y: Float): MathPoint {
        val k = (line.firstNode.y - line.secondNode.y) / (line.firstNode.x - line.secondNode.x)
        val b = line.firstNode.y - k*line.firstNode.x
        val perpendecular_k = -1/k
        val perpendecular_b = y-perpendecular_k*x

        val return_x = (perpendecular_b - b) / (k - perpendecular_k)
        val return_y = k*return_x + b

        return MathPoint(return_x, return_y)
    }

    fun isTouchOnSegment(line: Line, x: Float, y: Float): Boolean {
        return isTouchLeftSegment(line, x, y) && isTouchRightSegment(line, x, y)
    }

    fun isTouchRightSegment(line: Line, x: Float, y: Float): Boolean {
        val angleRight = scalarProduct(
            MathPoint(x - line.firstNode.x, y - line.firstNode.y),
            MathPoint(line.secondNode.x - line.firstNode.x, line.secondNode.y - line.firstNode.y)
        )

        return angleRight >= 0
    }

    fun isTouchLeftSegment(line: Line, x: Float, y: Float): Boolean {
        val angleLeft = scalarProduct(
            MathPoint(x - line.secondNode.x, y - line.secondNode.y),
            MathPoint(line.firstNode.x - line.secondNode.x, line.firstNode.y - line.secondNode.y)
        )

        return angleLeft >= 0
    }
}