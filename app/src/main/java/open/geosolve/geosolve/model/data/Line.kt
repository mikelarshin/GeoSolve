package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.model.helper.MathHelper.distanceBetweenPoints
import kotlin.math.sqrt

/*
 * TODO(DOC) Документировать математику в методе
 * TODO(CODE) Убрать хардкод POINT_SIZE
 * FIXME(CHECK) Работает ли корректно inRadius?
 */

class Line(
    var startNode: Node,
    var finalNode: Node
) : Element() {

    private val pointSize = 20f

    init {
        check(startNode != finalNode) { "Line get's the same node" }
    }

    fun deleteConnections() {
        startNode.finalLine = null
        finalNode.startLine = null
    }

    fun inRadius(x: Float, y: Float): Boolean {

        val dot = { x1: Float, y1: Float,
                    x2: Float, y2: Float ->
            (x1 * x2) + (y1 * y2)
        }

        val distanceStart = distanceBetweenPoints(startNode.x, startNode.y, x, y)
        val distanceFinal = distanceBetweenPoints(finalNode.x, finalNode.y, x, y)
        val lineLength = distanceBetweenPoints(startNode.x, startNode.y, finalNode.x, finalNode.y)

        val per: Float = (distanceStart + distanceFinal + lineLength) / 2

        val distance: Float = sqrt(
            per * (per - distanceStart) *
                    (per - distanceFinal) *
                    (per - lineLength)
        ) / lineLength

        return when {
            dot(
                x - startNode.x,
                y - startNode.y,
                finalNode.x - startNode.x,
                finalNode.y - startNode.y
            ) >= 0 &&
                    dot(
                        x - finalNode.x,
                        y - finalNode.y,
                        startNode.x - finalNode.x,
                        startNode.y - finalNode.y
                    ) >= 0 -> distance < pointSize / 40

            else -> false
        }
    }

    override fun toString(): String = "${startNode.char}${finalNode.char}"
}