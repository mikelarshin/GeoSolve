package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object Quadrangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean =
        figure.mNodes.size == 4
            && figure.mLines.size == 4
            && figure.mAngles.size == 4

    override fun setSubType(figure: Figure) {
        figure.subTypeSolve = when {
            Rectangle.isMatch(figure) -> Rectangle
            else -> UnknownFigure
        }
    }
}