package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.TypeFigure

object Polygon : TypeFigure(AnyFigure) {
    override fun isMatch(figure: Figure) = figure.mNodes.all { it.lines.size > 1 }
}