package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.model.MathUtil.distanceBetweenPoints
import open.geosolve.geosolve.model.data.generalized.Bind
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.math.sqrt

class Line(val startNode: Node, val finalNode: Node) : SolveGraph(), Bind, Element {

    override fun toString(): String = (startNode.char + finalNode.char.toString())

    init {
        check(startNode != finalNode){"Line constructor get the same Node"}
    }

    // Bind
    override val bindNodeList: MutableList<Node> = mutableListOf()

    override fun updateAllBind() {
        TODO()
    }

    override fun toBindNodeXY(node: Node, newX: Float, newY: Float) {
        TODO()
    }

    // Element
    override fun delConnection() {
        startNode.finalLine = null
        finalNode.startLine = null
    }

    override fun inRadius(x: Float, y: Float): Boolean {

        val dot = { x1: Float, y1: Float,
                    x2: Float, y2: Float ->
            (x1 * x2) + (y1 * y2)
        }

        val distanceStart: Float = distanceBetweenPoints(startNode, x, y)
        val distanceFin: Float = distanceBetweenPoints(finalNode, x, y)
        val lineLength: Float = distanceBetweenPoints(startNode, finalNode)

        val per: Float = (distanceStart + distanceFin + lineLength) / 2

        val distance: Float = sqrt(
            per * (per - distanceStart) *
                    (per - distanceFin) *
                    (per - lineLength)
        ) / lineLength

        val angleLeft = dot(
            x - startNode.x, y - startNode.y,
            finalNode.x - startNode.x, finalNode.y - startNode.y
        )
        val angleRight = dot(
            x - finalNode.x, y - finalNode.y,
            startNode.x - finalNode.x, startNode.y - finalNode.y
        )

        return when {
            angleLeft >= 0 && angleRight >= 0 -> distance < POINT_SIZE / 40
            else -> false
        }
    }
}