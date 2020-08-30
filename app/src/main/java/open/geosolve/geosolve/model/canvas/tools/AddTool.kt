package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.FigureController.addCircle
import open.geosolve.geosolve.model.canvas.FigureController.figure
import open.geosolve.geosolve.model.canvas.FigureList
import open.geosolve.geosolve.model.canvas.TouchEvent.onTouchCanvas
import open.geosolve.geosolve.model.canvas.TouchEvent.onTouchCircleLine
import open.geosolve.geosolve.model.canvas.TouchEvent.onTouchLine
import open.geosolve.geosolve.model.canvas.TouchEvent.onTouchPoint
import open.geosolve.geosolve.model.canvas.data.Circle
import open.geosolve.geosolve.model.canvas.data.Figure
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.Node

object AddTool : BaseTool() {
    var lastNode: Node? = null

    override fun onTouchMove(x: Float, y: Float) {
        super.onTouchMove(x, y)

        if (selectElement == null && moveQuantity == 6) {
            if (figure.isNotEmpty())
                FigureList.add(Figure()) // переходим на следующую фигуру

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