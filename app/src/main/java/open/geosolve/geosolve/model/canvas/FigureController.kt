package open.geosolve.geosolve.model.canvas

import open.geosolve.geosolve.model.canvas.data.*

object FigureController {

    val figure: Figure get() = FigureList.last()

    fun addNode(node: Node) {
        figure.mNodes.add(node)
    }

    fun addLine(line: Line) {
        figure.mLines.add(line)

        line.firstNode.lines.add(line)
        line.secondNode.lines.add(line)
    }

    fun addAngle(angle: Angle) {
        figure.mAngles.add(angle)

        angle.lines.forEach { it.angles.add(angle) }
        angle.nodes.forEach { it.angles.add(angle) }
    }

    fun addCircle(circle: Circle) {
        figure.mCircle = circle
    }

    fun updateFind() {
        if (find !in figure.mAngles + figure.mLines + figure.mCircle)
            find = null
    }
}