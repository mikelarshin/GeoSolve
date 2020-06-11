package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.GlobalFiguresController.figureList
import open.geosolve.geosolve.model.FigureController.addCircle
import open.geosolve.geosolve.model.FigureController.figure
import open.geosolve.geosolve.model.TouchEvent.onTouchCanvas
import open.geosolve.geosolve.model.TouchEvent.onTouchCircleLine
import open.geosolve.geosolve.model.TouchEvent.onTouchLine
import open.geosolve.geosolve.model.TouchEvent.onTouchPoint
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

object AddTool : BaseTool() {
    var lastNode: Node? = null

    override fun onTouchMove(x: Float, y: Float) {
        super.onTouchMove(x, y)

        if (selectElement == null && moveQuantity == 6) {
            if (figure.isNotEmpty())
                figureList.add(Figure()) // переходим на следующую фигуру

            val circle = Circle(Node(x, y))
            addCircle(circle)
            selectElement = circle
        }
    }

    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot) {
            when (selectElement) {
                is Node -> onTouchPoint(selectElement as Node)
                is Line -> onTouchLine(selectElement as Line, x, y)
                is Circle -> onTouchCircleLine(selectElement as Circle, x, y)
                null -> onTouchCanvas(x, y)
            }
        }

        if (figure.isClose())
            lastNode = null

        super.onTouchUp(x, y)
    }
}