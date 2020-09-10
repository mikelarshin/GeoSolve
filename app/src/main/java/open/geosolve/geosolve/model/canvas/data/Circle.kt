package open.geosolve.geosolve.model.canvas.data

import open.geosolve.geosolve.model.canvas.AllCircles
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.MathUtil.distanceBetweenPoints
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.LINE_WIDTH
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

    override fun toString() = centerNode.char

    var radiusLineList: MutableList<Line> = mutableListOf() // TODO(implement this)

    var radius: Float = 0f
        get() = systemCoordinate.convertDistance(field)

    //Circle
    fun moveRadius(x: Float, y: Float) {
        radius = distanceBetweenPoints(centerNode, x, y)

        updateAllBind()
    }

    // Bind
    override val bindNodes: MutableSet<Node> = mutableSetOf()

    override fun onBindNodeXY(node: Node, newX: Float, newY: Float) {
        val legacyVar = sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2))

        node.x = centerNode.x + (radius * (newX - centerNode.x) / legacyVar)

        node.y = centerNode.y + (radius * (newY - centerNode.y) / legacyVar)
    }

    // Element
    override fun remove() {
        centerNode.circle = null
        centerNode.remove()

        AllCircles.remove(this)
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val receivedRadius = distanceBetweenPoints(centerNode, x, y)

        val distanceToCircle = max(radius, receivedRadius) - min(radius, receivedRadius)

        val useTouchZone = LINE_WIDTH / 10
        return distanceToCircle < useTouchZone
    }
}