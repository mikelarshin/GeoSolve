package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.AllAngles
import open.geosolve.geosolve.model.MathUtil
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.draw.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.view.draw.PaintConstant.LINE_WIDTH

class Angle(val startLine: Line, val finalLine: Line) : SolveGraph(), Element {

    // all logic solve in abstract Element

    override fun toString(): String = "∠${(startNode.char.toString() + angleNode.char + finalNode.char)}"

    val startNode: Node
        get() = startLine.startNode
    val angleNode: Node
        get() = startLine.finalNode
    val finalNode: Node
        get() = finalLine.finalNode
    val lines: List<Line>
        get() = listOf(startLine, finalLine)

    init {
        check(startNode != finalNode){"Angle constructor get the same startNode & finalNode"}
        check(startNode != angleNode){"Angle constructor get the same startNode & angleNode"}
        check(angleNode != finalNode){"Angle constructor get the same angleNode & finalNode"}
        check(startLine != finalLine){"Angle constructor get the same Line"}
        check(startLine.finalNode == finalLine.startNode){"Angle constructor get wrong Line"}
    }

    // Element
    override fun inRadius(x: Float, y: Float): Boolean {
        val getRadius = MathUtil.distanceBetweenPoints(
            angleNode,
            SystemCoordinate.DECART.convertX(x),
            SystemCoordinate.DECART.convertX(y)
        )

        val distanceToAngleArc =
            if (ANGLE_ARC_RADIUS < getRadius)
                getRadius - ANGLE_ARC_RADIUS
            else
                ANGLE_ARC_RADIUS - getRadius

        val useTouchZone = LINE_WIDTH / 20
        return distanceToAngleArc < useTouchZone
    }

    override fun remove(){
        val nodeList = listOf(startNode, angleNode, finalNode)
        for (node in nodeList) {
            if (node.centerAngle == this)
                node.centerAngle = null
            node.neighborAngles.remove(this)
        }

        AllAngles.remove(this)
    }
}