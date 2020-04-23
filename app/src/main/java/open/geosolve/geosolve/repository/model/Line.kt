package open.geosolve.geosolve.repository.model

import android.util.Log
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.sqrt

class Line(
    val startNode: Node,
    val finalNode: Node
) : Element {

    var length: Float? = null

    init {
        // TODO Change with check
        if (startNode == finalNode)
            throw Exception("Line constructor get the same Node")
    }

    // TODO Doc this
    fun inRadius(x: Float, y: Float): Boolean {

        val dist = { x1: Float, y1: Float,
                     x2: Float, y2: Float ->
            hypot(x1 - x2, y1 - y2)
        }

        val dot = {x1: Float, y1: Float,
                   x2: Float, y2: Float ->
            (x1 * x2) + (y1 * y2)
        }

        val distanceStart: Float = dist(startNode.x, startNode.y, x, y)
        val distanceFin: Float = dist(finalNode.x, finalNode.y, x, y)
        val lineLength: Float = dist(startNode.x, startNode.y, finalNode.x, finalNode.y)

        val per: Float = (distanceStart + distanceFin + lineLength) / 2

        val distance: Float = sqrt(
            per * (per - distanceStart) *
                    (per - distanceFin) *
                    (per - lineLength)
        ) / lineLength

        return when{
            (dot(x - startNode.x, y - startNode.y, finalNode.x - startNode.x, finalNode.y - startNode.y) >= 0) and
                    (dot(x - finalNode.x, y - finalNode.y, startNode.x - finalNode.x, startNode.y - finalNode.y) >= 0) -> distance < 10

            else -> false
        }
    }
}