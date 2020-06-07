package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.AllLines
import open.geosolve.geosolve.model.math.MathUtil.getDistanceToLine
import open.geosolve.geosolve.model.math.MathUtil.isTouchOnSegment
import open.geosolve.geosolve.model.data.generalized.Bind
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.view.draw.PaintConstant.LINE_WIDTH

class Line(val startNode: Node, val finalNode: Node) : SolveGraph(), Bind, Element {

    override fun toString(): String = (startNode.char + finalNode.char)

    init {
        check(startNode != finalNode) { "Line constructor get the same Node" }
    }

    // Bind
    override val bindNodes: MutableList<Node> = mutableListOf()

    override fun updateAllBind() {
        TODO()
    }

    override fun toBindNodeXY(node: Node, newX: Float, newY: Float) {
        TODO()
    }

    // Element
    override fun remove() {
        startNode.neighborLines.remove(this)
        finalNode.neighborLines.remove(this)

        AllLines.remove(this)
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val useTouchZone = LINE_WIDTH / 30

        return if (isTouchOnSegment(this, x, y))
            getDistanceToLine(this, x, y) < useTouchZone
        else
            false
    }
}