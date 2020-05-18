package open.geosolve.geosolve.model

import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.sqrt

object MathUtil {
    fun distanceBetweenPoints(a: Node, b: Node) = hypot(a.x - b.x, a.y - b.y)
    fun distanceBetweenPoints(a: Node, x: Float, y: Float) = hypot(a.x - x, a.y - y)
    fun distanceBetweenPoints(x1: Float, y1: Float, x2: Float, y2: Float) = hypot(x1 - x2, y1 - y2)

    fun getAngle(startNode: Node, centerNode: Node, finalNode: Node): Float {
        val startVector = getVectorNode(centerNode, startNode)
        val finalVector = getVectorNode(centerNode, finalNode)

        val radianAngle =
            atan2(crossProduct(startVector, finalVector), scalarProduct(startVector, finalVector))
        return fromRadianToDegrees(radianAngle)
    }

//    private fun pseudoScalarProduct(a: MathPoint, b: MathPoint) = a.x * b.y - b.x * a.y
//
//    fun dont(angle: Angle, x: Float, y: Float): Float {
//        val BA = getVectorNode(angle.angleNode, angle.startNode)
//        val BC = getVectorNode(angle.angleNode, angle.finalNode)
//        val BP = getVectorNode(angle.angleNode, Node(x, y)) // TODO(fix to MathPoint)
//
//        val ads = pseudoScalarProduct(BC, BP)
//        val
////        BC x BP
////        BP x BA
//    }


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

    private data class MathPoint(val x: Float, val y: Float)
}