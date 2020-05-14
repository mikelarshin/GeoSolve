package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.data.Figure

interface SolveFigure {
    fun isMatch(figure: Figure): Boolean
    fun setGraphs(figure: Figure)
    fun setSubType(figure: Figure)
//    fun isCorrect(figure: Figure): Boolean
}
