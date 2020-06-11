package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object UnknownFigure : SolveFigure {
    override fun isMatch(figure: Figure) = false
    override fun setGraphs(figure: Figure) {}
    override fun setSubType(figure: Figure) {}
}