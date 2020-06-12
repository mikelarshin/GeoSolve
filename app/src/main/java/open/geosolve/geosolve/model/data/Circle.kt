package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.AllCircles
import open.geosolve.geosolve.model.math.MathUtil.distanceBetweenPoints
import open.geosolve.geosolve.model.data.generalized.Bind
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.view.draw.SystemCoordinate.ABSOLUTE
import open.geosolve.geosolve.view.view.draw.PaintConstant.LINE_WIDTH
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class Circle(val centerNode: Node) : SolveGraph(), Bind, Element {

    // all solve logic in abstract SolveGraph

    init {
        centerNode.char = "O"
        centerNode.circle = this
    }

    override fun toString() = "" // TODO(implement)

    var radiusLineList: MutableList<Line> = mutableListOf() // TODO(implement this)

    var drawRadius: Float = 0f
    var decartRadius: Float = 0f

    //Circle
    fun moveRadius(x: Float, y: Float) {
        drawRadius = distanceBetweenPoints(
            ABSOLUTE.convertX(centerNode.x), ABSOLUTE.convertY(centerNode.y),
            ABSOLUTE.convertX(x), ABSOLUTE.convertY(y)
        )

        decartRadius = distanceBetweenPoints(centerNode, x, y)

        updateAllBind()
    }

    // Bind
    override val bindNodes: MutableSet<Node> = mutableSetOf()

    override fun toBindNodeXY(node: Node, newX: Float, newY: Float) {
        node.x = centerNode.x + (decartRadius * (newX - centerNode.x) /
                sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2)))

        node.y = centerNode.y + (decartRadius * (newY - centerNode.y) /
                sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2)))
    }

    // Element
    override fun remove() { // TODO(rewrite remove system)
        centerNode.circle = null
        centerNode.remove()

        AllCircles.remove(this)
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val receivedRadius = distanceBetweenPoints(
            centerNode,
            x, y
        )

        val distanceToCircle = max(decartRadius, receivedRadius) - min(decartRadius, receivedRadius)

        val useTouchZone = LINE_WIDTH / 10
        return distanceToCircle < useTouchZone
    }
}