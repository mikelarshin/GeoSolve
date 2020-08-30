package open.geosolve.geosolve.model.canvas.data

import open.geosolve.geosolve.model.canvas.AllLines
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.MathUtil.getDistanceToLine
import open.geosolve.geosolve.model.canvas.math.MathUtil.getPointProjectToLine
import open.geosolve.geosolve.model.canvas.math.MathUtil.isTouchLeftSegment
import open.geosolve.geosolve.model.canvas.math.MathUtil.isTouchOnSegment
import open.geosolve.geosolve.model.canvas.math.MathUtil.isTouchRightSegment
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.LINE_WIDTH

class Line(first: Node, second: Node) : SolveGraph(), Bind, Element {

    // all solve logic in abstract SolveGraph

    val nodes = setOf(first, second)
    val angles = mutableSetOf<Angle>()
    val firstNode = nodes.first()
    val secondNode = nodes.first { it != firstNode }

    override fun toString(): String = "$firstNode$secondNode"

    init {
        check(firstNode != secondNode) { "Line constructor get the same Node" }
    }

    // Bind
    override val bindNodes: MutableSet<Node> = mutableSetOf()

    override fun toBindNodeXY(node: Node, newX: Float, newY: Float) {
        when {
            isTouchOnSegment(this, newX, newY) -> {
                val point = getPointProjectToLine(this, newX, newY)
                node.x = point.x
                node.y = point.y
            }
            isTouchLeftSegment(this, newX, newY) -> {
                node.x = firstNode.x
                node.y = firstNode.y
            }
            isTouchRightSegment(this, newX, newY) -> {
                node.x = secondNode.x
                node.y = secondNode.y
            }
        }
    }

    // Element
    override fun remove() {
//        firstNode.lines.remove(this)
//        secondNode.lines.remove(this)
//        angles.forEach { it.remove() }

        AllLines.remove(this)
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val useTouchZone = LINE_WIDTH / 30

        return if (isTouchOnSegment(this, x, y))
            getDistanceToLine(this, x, y) < useTouchZone
        else
            false
    }

    fun equal(line: Line): Boolean =
        (this.firstNode == line.firstNode && this.secondNode == line.secondNode) ||
                (this.firstNode == line.secondNode && this.secondNode == line.firstNode)
}