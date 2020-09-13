package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.math.XYPoint
import java.io.Serializable

interface Tool : Serializable {
    fun onTouchDown(point: XYPoint) { onTouch(point) }
    fun onTouchMove(point: XYPoint) { onTouch(point) }
    fun onTouchUp(point: XYPoint) { onTouch(point) }

    fun cycleTouch(point: XYPoint) {
        onTouchDown(point)
        onTouchMove(point)
        onTouchUp(point)
    }

    fun onTouch(point: XYPoint) {}
}


// То как у Валеры
//    fun onTouchCanvas(figure: Figure, x: Float, y: Float)
//    fun onTouchNode(figure: Figure, node: Node)
//    fun onTouchLine(figure: Figure, line: Line)
//    fun onTouchInFigure(figure: Figure)

