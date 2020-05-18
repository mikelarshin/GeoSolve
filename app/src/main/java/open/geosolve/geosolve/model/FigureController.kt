package open.geosolve.geosolve.model

import open.geosolve.geosolve.App.Companion.figureList
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.*

object FigureController {

    val figure: Figure get() = figureList.last()

    fun addNode(node: Node) {
        figure.mNodes.add(node)
    }

    fun addLine(line: Line) {
        figure.mLines.add(line)

        line.startNode.neighborLines.add(line)
        line.finalNode.neighborLines.add(line)
    }

    fun addAngle(angle: Angle) {
        figure.mAngles.add(angle)

        angle.startLine.startNode.neighborAngles.add(angle)
        angle.startLine.finalNode.centerAngle = angle
        angle.finalLine.finalNode.neighborAngles.add(angle)
    }

    fun addCircle(x: Float, y: Float) {
        figure.mCircle = Circle(Node(x, y))
    }

    fun removeDependent() {
        for (element in figure.mAngles + figure.mLines + figure.mCircle)
            if (find == element)
                find = null

    }
}