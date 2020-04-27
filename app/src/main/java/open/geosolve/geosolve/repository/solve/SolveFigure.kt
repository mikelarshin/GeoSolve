package open.geosolve.geosolve.repository.solve

import open.geosolve.geosolve.repository.model.Figure

interface SolveFigure {
    fun isMatch(figure: Figure): Boolean
    fun setGraphs(figure: Figure)
}