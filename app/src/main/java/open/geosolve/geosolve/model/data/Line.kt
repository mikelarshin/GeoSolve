package open.geosolve.geosolve.model.data

import kotlin.math.hypot
import kotlin.math.sqrt

class Line(val startNode: Node, val finalNode: Node) : Element() {

    //    all logic solve in abstract Element

    init {
        if (startNode == finalNode)
            throw Exception("Line constructor get the same Node")
    }

    fun delConnection(){
        startNode.startLine = null
        finalNode.finalLine = null
    }

    // TODO Doc this
    fun inRadius(x: Float, y: Float): Boolean {

        val dist = { x1: Float, y1: Float,
                     x2: Float, y2: Float ->
            hypot(x1 - x2, y1 - y2)
        }

        val dot = { x1: Float, y1: Float,
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
            dot(x - startNode.x, y - startNode.y, finalNode.x - startNode.x, finalNode.y - startNode.y) >= 0 &&
                    dot(x - finalNode.x, y - finalNode.y, startNode.x - finalNode.x, startNode.y - finalNode.y) >= 0 -> distance < 10

            else -> false
        }
    }
}