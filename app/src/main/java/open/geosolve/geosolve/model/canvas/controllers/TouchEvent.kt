package open.geosolve.geosolve.model.canvas.controllers

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeCanvasData
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeFigure
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.allLines
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.elements.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.math.XYPoint

object TouchEvent {

    fun onTouchCanvas(point: XYPoint) {
        onTouch(point)
    }

    fun onTouchLine(line: Line, point: XYPoint) {
        onTouch(point, line)
    }

    fun onTouchCircleLine(circle: Circle, point: XYPoint) {
        onTouch(point, circle)
    }

    private fun onTouch(point: XYPoint, bind: Bind? = null) {
        val newNode = point.toNode()
        newNode.bind = bind
        activeFigure.addNode(newNode)

        onTouchPoint(newNode)
    }

    fun onTouchPoint(touchNode: Node) {
        if (touchNode != activeCanvasData.lastNode && activeCanvasData.lastNode != null) {  // если нажатая точка и конечная точка линии не равны и прошлая точка есть

            val newLine = Line(activeCanvasData.lastNode!!, touchNode)
            if (!allLines.any { it.equal(newLine) }) { // добавляем линию только если нету такой же
                activeFigure.addLine(newLine)

                activeFigure.updateNodes()
                activeFigure.updateLines()
                activeFigure.updateAngles()
            }
        }
        activeCanvasData.lastNode = touchNode
    }
}
