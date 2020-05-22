package open.geosolve.geosolve.model.helper

import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

/*
 * TODO(DOC) Документировать
 * TODO(CODE) Использовать @inject в Figure, избегая пробрасываний
 */

@Suppress("MemberVisibilityCanBePrivate")
object FigureManipulator {

    private lateinit var figure: Figure

    fun linkFigure(figure: Figure) {
        this.figure = figure
    }

    fun addNode(node: Node) {
        figure.nodes.add(node)
    }

    fun addLine(startNode: Node, finalNode: Node) {
        Line(startNode, finalNode).let { line ->

            figure.lines.add(line)

            startNode.finalLine = line
            finalNode.startLine = line
        }
    }

    fun addAngle(startLine: Line, finalLine: Line) {
        Angle(startLine, finalLine).let { angle ->
            figure.angles.add(angle)

            startLine.startNode.startAngle = angle
            startLine.finalNode.centerAngle = angle
            finalLine.finalNode.finalAngle = angle
        }
    }

    fun deleteNode(x: Float, y: Float) {
        findNode(x, y)?.let { deleteNode(it) }
    }

    fun deleteNode(node: Node) {

        for (angle in listOf(
            node.startAngle,
            node.centerAngle,
            node.finalAngle
        )) {
            if (figure.find == angle) {
                figure.find = null
            }

            figure.angles.remove(angle)
        }

        for (line in listOf(
            node.startLine,
            node.finalLine
        )) {
            if (figure.find == line) {
                figure.find = null
            }

            figure.lines.remove(line)
        }

        node.deleteConnections()
        figure.nodes.remove(node)
    }

    fun findNode(x: Float, y: Float): Node? {
        figure.nodes.forEach { node ->
            if (node.inRadius(x, y)) {
                return node
            }
        }

        return null
    }
}