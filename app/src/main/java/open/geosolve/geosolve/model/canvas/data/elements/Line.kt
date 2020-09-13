package open.geosolve.geosolve.model.canvas.data.elements

import open.geosolve.geosolve.model.canvas.controllers.AllLines
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.MathUtil.getDistanceToLine
import open.geosolve.geosolve.model.canvas.math.MathUtil.getPointProjectToLine
import open.geosolve.geosolve.model.canvas.math.MathUtil.isTouchLeftSegment
import open.geosolve.geosolve.model.canvas.math.MathUtil.isTouchOnSegment
import open.geosolve.geosolve.model.canvas.math.MathUtil.isTouchRightSegment
import open.geosolve.geosolve.model.canvas.math.XY
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.LINE_WIDTH

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

    override fun onBindNodeXY(node: Node, newPoint: XYPoint) {
        val point: XY = when {
            isTouchOnSegment(this, newPoint) -> getPointProjectToLine(this, newPoint)
            isTouchLeftSegment(this, newPoint) -> firstNode
            isTouchRightSegment(this, newPoint) -> secondNode
            else -> null!!
        }

        node.x = point.x.also { node.y = point.y } // присвоение node XY point
    }

    // Element
    override fun remove() {
//        firstNode.lines.remove(this)
//        secondNode.lines.remove(this)
//        angles.forEach { it.remove() }

        AllLines.remove(this)
    }

    override fun inRadius(point: XYPoint): Boolean {
        val useTouchZone = LINE_WIDTH / 30

        return if (isTouchOnSegment(this, point))
            getDistanceToLine(this, point) < useTouchZone
        else
            false
    }

    fun equal(line: Line): Boolean =
        (this.firstNode == line.firstNode && this.secondNode == line.secondNode) ||
                (this.firstNode == line.secondNode && this.secondNode == line.firstNode)
}