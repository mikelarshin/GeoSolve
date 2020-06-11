package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.AllAngles
import open.geosolve.geosolve.AllNodes
import open.geosolve.geosolve.initOnce
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.math.MathUtil
import open.geosolve.geosolve.model.math.MathUtil.isPointInAngle
import open.geosolve.geosolve.view.view.draw.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.view.draw.PaintConstant.LINE_WIDTH
import kotlin.math.max
import kotlin.math.min

class Angle(private val startLine: Line, private val finalLine: Line) : SolveGraph(), Element {

    // all solve logic in abstract SolveGraph

    var startNode: Node by initOnce()
    var angleNode: Node by initOnce()
    var finalNode: Node by initOnce()

    init {
        check(startLine != finalLine) { "Линии угла должны быть разными" }
        check(isCorrectNodes(startLine, finalLine)) { "В угле должно быть 3 уникальных точки" }

        startNode =
            startLine.nodes.first { !finalLine.nodes.contains(it) } // стартовая точка - только в стартовой линии
        angleNode =
            startLine.nodes.first { finalLine.nodes.contains(it) }  // угловая точка - есть во всех линиях
        finalNode =
            finalLine.nodes.first { !startLine.nodes.contains(it) } // финальная точка - только в финальной линии
    }

    companion object {
        fun isCorrectNodes(startLine: Line, finalLine: Line) =
            (startLine.nodes + finalLine.nodes).size == 3
    }


    val nodes
        get() = listOf(startNode, angleNode, finalNode)
    val lines
        get() = listOf(startLine, finalLine)

    // Element
    override fun inRadius(x: Float, y: Float): Boolean {
        val receivedRadius = MathUtil.distanceBetweenPoints(angleNode, x, y)

        val myRadius = ANGLE_ARC_RADIUS / 50

        val distanceToAngleArc = max(myRadius, receivedRadius) - min(myRadius, receivedRadius)

        val useTouchZone = LINE_WIDTH / 20

        return (distanceToAngleArc < useTouchZone) && isPointInAngle(this, x, y)
    }

    override fun remove() {
        val nodeList = listOf(startNode, angleNode, finalNode)
        for (node in nodeList) {
            node.angles.remove(this)
        }

        AllAngles.remove(this)
    }

    override fun toString(): String = "∠$startNode$angleNode$finalNode"

    fun equal(startLine: Line, finalLine: Line): Boolean {
        return (startLine.equal(this.startLine) && finalLine.equal(this.finalLine)) ||
                (startLine.equal(this.finalLine) && finalLine.equal(this.startLine))
    }
}
