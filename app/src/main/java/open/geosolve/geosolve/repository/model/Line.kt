package open.geosolve.geosolve.repository.model

import kotlin.math.hypot
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

        startNode.neighborLines.add(this)
        finalNode.neighborLines.add(this)
    }

    // TODO Doc this
    fun inRadius(x: Float, y: Float): Boolean {

        val distanceStart: Float = hypot(x - startNode.x, y - startNode.y)
        val distanceFin: Float = hypot(x - finalNode.x, y - finalNode.y)
        val lineLength: Float = hypot(startNode.x - finalNode.x, startNode.y - finalNode.y)

        val per: Float = (distanceStart + distanceFin + lineLength) / 2

        val distance: Float = sqrt(
            per * (per - distanceStart) *
                    (per - distanceFin) *
                    (per - lineLength)
        ) / lineLength

        return distance < 30
    }

}