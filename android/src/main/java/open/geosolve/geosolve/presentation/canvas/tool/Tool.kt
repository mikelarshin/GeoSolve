package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

/*
 * FIXME(CODE) onTouchLine реализован, но не вызывается
 * TODO(CODE) onTouchInFigure не реализован, не вызывается
 */

interface Tool {

    fun onTouchCanvas(figure: Figure, x: Float, y: Float)

    fun onTouchNode(figure: Figure, node: Node)
    fun onTouchLine(figure: Figure, line: Line)
    // fun onTouchInFigure(figure: Figure)
}