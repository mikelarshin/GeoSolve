package open.geosolve.geosolve.model.tools

import java.io.Serializable

interface Tool : Serializable {
    fun onTouchDown(x: Float, y: Float)
    fun onTouchMove(x: Float, y: Float)
    fun onTouchUp(x: Float, y: Float)

    fun cycleTouch(x: Float, y: Float) {
        onTouchDown(x, y)
        onTouchMove(x, y)
        onTouchUp(x, y)
    }
}


// То как у Валеры
//    fun onTouchCanvas(figure: Figure, x: Float, y: Float)
//    fun onTouchNode(figure: Figure, node: Node)
//    fun onTouchLine(figure: Figure, line: Line)
//    fun onTouchInFigure(figure: Figure)

