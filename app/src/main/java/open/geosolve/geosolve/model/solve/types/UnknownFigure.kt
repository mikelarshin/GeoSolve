package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object UnknownFigure : SolveFigure {
    override fun isMatch(figure: Figure) = false
}