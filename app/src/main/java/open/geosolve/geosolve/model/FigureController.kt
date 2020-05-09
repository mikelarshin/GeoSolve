package open.geosolve.geosolve.model

import open.geosolve.geosolve.App.Companion.figureList
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.*

class FigureController {

    val figure: Figure get() = figureList.last()

    fun addNode(node: Node) {
        figure.mNodes.add(node)
    }

    fun addLine(startNode: Node, finalNode: Node) {
        val line = Line(startNode, finalNode)
        figure.mLines.add(line)

        startNode.finalLine = line
        finalNode.startLine = line
    }

    fun addAngle(startLine: Line, finalLine: Line) {
        val angle = Angle(startLine, finalLine)
        figure.mAngles.add(angle)

        startLine.startNode.startAngle = angle
        startLine.finalNode.centerAngle = angle
        finalLine.finalNode.finalAngle = angle
    }

    fun addCircle(x: Float, y: Float){
        figure.mCircle = Circle(Node(x, y))
    }

    fun closeFigureInStartPoint(){
        val closeNode = figure.mNodes.first()
        addLine(figure.mNodes.last(), closeNode)
        addAngle(closeNode.startLine?.startNode?.startLine!!, closeNode.startLine!!)
        addAngle(closeNode.startLine!!, closeNode.finalLine!!)
    }

    fun removeDependent(){
        for (element in figure.mAngles + figure.mLines + figure.mCircle)
            if (find == element)
                find = null
    }
}