package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.model.MathUtil.distanceBetweenPoints
import open.geosolve.geosolve.model.data.generalized.*
import open.geosolve.geosolve.model.status.SystemCoordinate.ABSOLUTE
import open.geosolve.geosolve.model.status.SystemCoordinate.DECART
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.math.pow
import kotlin.math.sqrt

class Circle(val centerNode: Node) : SolveGraph(), Movable, Bind, Element {

    init {
        centerNode.char = 'O'
    }

    var radiusLineList: MutableList<Line> = mutableListOf()

    var drawRadius: Float = 0f
    var decartRadius: Float = 0f

    // Bind
    override val bindNodeList: MutableList<Node> = mutableListOf()

    override fun updateAllBind() {
        bindNodeList.forEach { it.updateXYbyBind() }
    }

    override fun toBindNodeXY(node: Node, newX: Float, newY: Float) {
        node.x = centerNode.x + (decartRadius * (newX - centerNode.x) /
                sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2)))

        node.y = centerNode.y + (decartRadius * (newY - centerNode.y) /
                sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2)))
    }

    // Movable
    override fun move(x: Float, y: Float) {
        drawRadius = distanceBetweenPoints(
            ABSOLUTE.convertX(centerNode.x), ABSOLUTE.convertY(centerNode.y),
            ABSOLUTE.convertX(x), ABSOLUTE.convertY(y)
        )

        decartRadius = distanceBetweenPoints(centerNode, x, y)

        updateAllBind()
    }

    // Element
    override fun delConnection() {
        TODO("Not yet implemented")
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val newRadius = distanceBetweenPoints(
            centerNode,
            DECART.convertX(x),
            DECART.convertX(y)
        )

        val distanceToCircle =
            if (decartRadius < newRadius)
                newRadius - decartRadius
            else
                decartRadius - newRadius

        val bufferUseTouch = POINT_SIZE / 20
        return distanceToCircle < bufferUseTouch
    }
}