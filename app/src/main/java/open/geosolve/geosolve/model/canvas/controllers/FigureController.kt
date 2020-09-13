package open.geosolve.geosolve.model.canvas.controllers

import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.elements.Node

object FigureController {

    fun addNode(node: Node) {
        Figure.mNodes.add(node)
    }

    fun addLine(line: Line) {
        Figure.mLines.add(line)

        line.firstNode.lines.add(line)
        line.secondNode.lines.add(line)
    }

    fun addAngle(angle: Angle) {
        Figure.mAngles.add(angle)

        angle.lines.forEach { it.angles.add(angle) }
        angle.nodes.forEach { it.angles.add(angle) }
    }

    fun addCircle(circle: Circle) {
        Figure.mCircle = circle
    }
}