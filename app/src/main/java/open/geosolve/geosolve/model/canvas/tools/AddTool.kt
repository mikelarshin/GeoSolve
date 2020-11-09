package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.controllers.TouchEvent.onTouchCanvas
import open.geosolve.geosolve.model.canvas.controllers.TouchEvent.onTouchCircleLine
import open.geosolve.geosolve.model.canvas.controllers.TouchEvent.onTouchLine
import open.geosolve.geosolve.model.canvas.controllers.TouchEvent.onTouchPoint
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeFigure
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeFigureList
import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.elements.Node
import open.geosolve.geosolve.model.canvas.math.XYPoint

object AddTool : BaseTool() {
    var lastNode: Node? = null

    override fun onTouchMove(point: XYPoint) {
        super.onTouchMove(point)

        if (selectElement == null && moveQuantity == 6) {
            if (activeFigure.isNotEmpty())
                activeFigureList.add(Figure()) // переходим на следующую фигуру

            val circle = Circle(point.toNode())
            activeFigure.addCircle(circle)
            selectElement = circle
        }
    }

    override fun onTouchElement(point: XYPoint) {
        when (selectElement) {
            is Node -> onTouchPoint(selectElement as Node)
            is Line -> onTouchLine(selectElement as Line, point)
            is Circle -> onTouchCircleLine(selectElement as Circle, point)
            null -> onTouchCanvas(point)
        }

        if (activeFigure.isClose())
            lastNode = null
    }
}