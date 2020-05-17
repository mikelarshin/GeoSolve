package open.geosolve.geosolve.model

import android.util.Log
import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.systemCoordinate
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.status.SystemCoordinate
import kotlin.math.*

object MathUtil {
    fun distanceBetweenPoints(a: Node, b: Node) = hypot(a.x - b.x, a.y - b.y)
    fun distanceBetweenPoints(a: Node, x: Float, y: Float) = hypot(a.x - x, a.y - y)
    fun distanceBetweenPoints(x1: Float, y1: Float, x2: Float, y2: Float) = hypot(x1 - x2, y1 - y2)

    fun getDrawAngleValue(angle: Angle): Float {
        return getAngle(angle.startNode, angle.angleNode, angle.finalNode).absoluteValue
    }

    fun getStartAngle(angle: Angle): Float {
        systemCoordinate = SystemCoordinate.DECART
        val startNode = Node(angle.angleNode.x, angle.angleNode.y + 1f)
        systemCoordinate = SystemCoordinate.ABSOLUTE

        val angleToStart = getAngle(startNode, angle.angleNode, angle.startNode)
        val angleToFinal = getAngle(startNode, angle.angleNode, angle.finalNode)

        Log.e("angles", "angleToStart: ${angleToStart + 65f}, angleToFinal: ${angleToFinal + 65f}")

        return min(angleToStart, angleToFinal)
    }

    private fun getAngle(startNode: Node, centerNode: Node, finalNode: Node): Float {
        val startVector = getVectorNode(centerNode, startNode)
        val finalVector = getVectorNode(centerNode, finalNode)

        val radianAngle = atan2(crossProduct(startVector, finalVector), scalarProduct(startVector, finalVector))
        return fromRadianToDegrees(radianAngle)
    }

    private fun fromRadianToDegrees(x: Float): Float = x * 180 / PI.toFloat()
    private fun crossProduct(a: MathPoint, b: MathPoint) = a.x * b.y - a.y * b.x
    private fun scalarProduct(a: MathPoint, b: MathPoint) = a.x * b.x + a.y * b.y

    private fun getVectorNode(a: Node, b: Node) = MathPoint(b.x - a.x, b.y - a.y)

    private data class MathPoint(val x: Float, val y: Float)
}