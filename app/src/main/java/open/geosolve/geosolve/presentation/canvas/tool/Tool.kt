package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

interface Tool {

    fun onTouchCanvas(figure: Figure, x: Float, y: Float)

    fun onTouchNode(figure: Figure, node: Node)
    fun onTouchLine(figure: Figure, line: Line)
    /* FIXME Unimplemented onTouchInFigure in CanvasScreenPresenter
       fun onTouchInFigure(figure: Figure)
     */
}