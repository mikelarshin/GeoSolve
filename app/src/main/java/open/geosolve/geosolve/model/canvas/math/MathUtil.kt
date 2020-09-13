package open.geosolve.geosolve.model.canvas.math

import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import kotlin.math.*


object MathUtil {

    // Distance
    fun distanceBetweenPoints(a: XY, b: XY) = hypot(a.x - b.x, a.y - b.y)

    fun getDegree(angle: Angle): Float {
        return getDegree(angle.startNode, angle.angleNode, angle.finalNode)
    }

    fun getDegree(startNode: XY, centerNode: XY, finalNode: XY): Float {
        val startVector = getVectorPoint(centerNode, startNode)
        val finalVector = getVectorPoint(centerNode, finalNode)

        val radianAngle =
            atan2(crossProduct(startVector, finalVector), scalarProduct(startVector, finalVector))

        return fromRadianToDegrees(radianAngle)
    }

    fun isPointInAngle(angle: Angle, point: XYPoint): Boolean {
        val startAngle = getDegree(point, angle.angleNode, angle.startNode)
        val finalAngle = getDegree(point, angle.angleNode, angle.finalNode)

        return (startAngle.absoluteValue + finalAngle.absoluteValue) < 180
    }

    // formulas
    private fun fromRadianToDegrees(r: Float): Float = r * 180 / PI.toFloat()
    private fun crossProduct(a: XY, b: XY) = a.x * b.y - a.y * b.x
    private fun scalarProduct(a: XY, b: XY) = a.x * b.x + a.y * b.y // this is dot()

    private fun getVectorPoint(a: XY, b: XY) = XYPoint(b.x - a.x, b.y - a.y)

    // Line
    fun getDistanceToLine(line: Line, point: XYPoint): Float {
        val distanceStart: Float = distanceBetweenPoints(line.firstNode, point)
        val distanceFin: Float = distanceBetweenPoints(line.secondNode, point)
        val lineLength: Float = distanceBetweenPoints(line.firstNode, line.secondNode)

        val per = (distanceStart + distanceFin + lineLength) / 2

        return sqrt(per * (per - distanceStart) * (per - distanceFin) * (per - lineLength)) / lineLength
    }

    fun getPointProjectToLine(line: Line, point: XYPoint): XYPoint {
        val k = (line.firstNode.y - line.secondNode.y) / (line.firstNode.x - line.secondNode.x)
        val b = line.firstNode.y - k*line.firstNode.x
        val perpendecular_k = -1/k
        val perpendecular_b = point.y-perpendecular_k*point.x

        val return_x = (perpendecular_b - b) / (k - perpendecular_k)
        val return_y = k*return_x + b

        return XYPoint(return_x, return_y)
    }

    fun isTouchOnSegment(line: Line, point: XYPoint): Boolean {
        return isTouchLeftSegment(line, point) && isTouchRightSegment(line, point)
    }

    fun isTouchRightSegment(line: Line, point: XYPoint): Boolean {
        val angleRight = scalarProduct(
            XYPoint(point.x - line.firstNode.x, point.y - line.firstNode.y),
            XYPoint(line.secondNode.x - line.firstNode.x, line.secondNode.y - line.firstNode.y)
        )

        return angleRight >= 0
    }

    fun isTouchLeftSegment(line: Line, point: XYPoint): Boolean {
        val angleLeft = scalarProduct(
            XYPoint(point.x - line.secondNode.x, point.y - line.secondNode.y),
            XYPoint(line.firstNode.x - line.secondNode.x, line.firstNode.y - line.secondNode.y)
        )

        return angleLeft >= 0
    }
}