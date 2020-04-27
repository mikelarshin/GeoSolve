package open.geosolve.geosolve.repository.solve.type

import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.solve.SolveFigure

object UnknownFigure : SolveFigure {
    override fun isMatch(figure: Figure) = false
    override fun setGraphs(figure: Figure) {}
}