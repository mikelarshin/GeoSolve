package open.geosolve.geosolve.model.canvas.data.elements

import open.geosolve.geosolve.model.canvas.controllers.AllAngles
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.MathUtil
import open.geosolve.geosolve.model.canvas.math.MathUtil.isPointInAngle
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.view.canvas.draw.DrawConstant.scale
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.LINE_WIDTH
import kotlin.math.max
import kotlin.math.min

class Angle(private val startLine: Line, private val finalLine: Line) : SolveGraph(), Element {

    // all solve logic in abstract SolveGraph

    val startNode: Node
    val angleNode: Node
    val finalNode: Node

    init {
        check(startLine != finalLine) { "Линии угла должны быть разными" }
        check(isCorrectNodes(startLine, finalLine)) { "В угле должно быть 3 уникальных точки" }

        startNode = startLine.nodes.first { !finalLine.nodes.contains(it) } // стартовая точка - только в стартовой линии
        angleNode = startLine.nodes.first { finalLine.nodes.contains(it) }  // угловая точка - есть во всех линиях
        finalNode = finalLine.nodes.first { !startLine.nodes.contains(it) } // финальная точка - только в финальной линии
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
    override fun inRadius(point: XYPoint): Boolean {
        val receivedRadius = MathUtil.distanceBetweenPoints(angleNode, point)

        val myRadius = ANGLE_ARC_RADIUS / scale

        val distanceToAngleArc = max(myRadius, receivedRadius) - min(myRadius, receivedRadius)

        val useTouchZone = LINE_WIDTH / 10

        return (distanceToAngleArc < useTouchZone) && isPointInAngle(this, point)
    }

    override fun remove() {
//        startLine.remove()
//        finalLine.remove()

        AllAngles.remove(this)
    }

    override fun toString(): String = "∠$startNode$angleNode$finalNode"
    fun toSmallString(): String = "∠$angleNode"

    fun equal(startLine: Line, finalLine: Line): Boolean {
        return (startLine.equal(this.startLine) && finalLine.equal(this.finalLine)) ||
                (startLine.equal(this.finalLine) && finalLine.equal(this.startLine))
    }
}
