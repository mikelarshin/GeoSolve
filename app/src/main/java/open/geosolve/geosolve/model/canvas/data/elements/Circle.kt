package open.geosolve.geosolve.model.canvas.data.elements

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.allCircles
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.MathUtil.distanceBetweenPoints
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.view.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.LINE_WIDTH
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
    fun moveRadius(point: XYPoint) {
        radius = distanceBetweenPoints(centerNode, point)

        updateAllBind()
    }

    // Bind
    override val bindNodes: MutableSet<Node> = mutableSetOf()

    override fun onBindNodeXY(node: Node, newPoint: XYPoint) {
        val legacyVar = sqrt((newPoint.x - centerNode.x).pow(2) + (newPoint.y - centerNode.y).pow(2))

        node.x = centerNode.x + (radius * (newPoint.x - centerNode.x) / legacyVar)

        node.y = centerNode.y + (radius * (newPoint.y - centerNode.y) / legacyVar)
    }

    // Element
    override fun remove() {
        centerNode.circle = null
        centerNode.remove()

        allCircles.remove(this)
    }

    override fun inRadius(point: XYPoint): Boolean {
        val receivedRadius = distanceBetweenPoints(centerNode, point)

        val distanceToCircle = max(radius, receivedRadius) - min(radius, receivedRadius)

        val useTouchZone = LINE_WIDTH / 10
        return distanceToCircle < useTouchZone
    }
}