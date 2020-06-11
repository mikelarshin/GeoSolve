package open.geosolve.geosolve.model.tools

interface Tool {
    fun onTouchDown(x: Float, y: Float)
    fun onTouchMove(x: Float, y: Float)
    fun onTouchUp(x: Float, y: Float)
}



// То как у Валеры
//    fun onTouchCanvas(figure: Figure, x: Float, y: Float)
//    fun onTouchNode(figure: Figure, node: Node)
//    fun onTouchLine(figure: Figure, line: Line)
//    fun onTouchInFigure(figure: Figure)

