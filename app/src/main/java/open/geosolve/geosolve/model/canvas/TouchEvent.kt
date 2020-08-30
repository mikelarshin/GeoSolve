package open.geosolve.geosolve.model.canvas

import open.geosolve.geosolve.model.canvas.ElementUpdaters.updateAngles
import open.geosolve.geosolve.model.canvas.ElementUpdaters.updateLines
import open.geosolve.geosolve.model.canvas.ElementUpdaters.updateNodes
import open.geosolve.geosolve.model.canvas.FigureController.addLine
import open.geosolve.geosolve.model.canvas.FigureController.addNode
import open.geosolve.geosolve.model.canvas.data.Circle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.tools.AddTool.lastNode

object TouchEvent {
    fun onTouchCanvas(touchX: Float, touchY: Float) {
        onTouch(touchX, touchY)
    }

    fun onTouchLine(line: Line, x: Float, y: Float) {
        onTouch(x, y, line)
    }

    fun onTouchCircleLine(circle: Circle, x: Float, y: Float) {
        onTouch(x, y, circle)
    }

    private fun onTouch(touchX: Float, touchY: Float, bind: Bind? = null) {
        val newNode = Node(touchX, touchY)
        newNode.bind = bind
        addNode(newNode)

        onTouchPoint(newNode)
    }

    fun onTouchPoint(touchNode: Node) {
        if (touchNode != lastNode && lastNode != null) {  // если нажатая точка и конечная точка линии не равны и прошлая точка есть

            val newLine = Line(lastNode!!, touchNode)
            if (!AllLines.any { it.equal(newLine) }) { // добавляем линию только если нету такой же
                addLine(newLine)

                updateNodes()
                updateLines()
                updateAngles()
            }
        }
        lastNode = touchNode
    }
}
