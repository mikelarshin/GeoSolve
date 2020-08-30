package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.canvas.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object UnknownFigure : SolveFigure {
    override fun isMatch(figure: Figure) = false
}