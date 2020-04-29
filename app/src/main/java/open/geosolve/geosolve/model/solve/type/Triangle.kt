package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object Triangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 3 && figure.mLines.size == 3 && figure.mAngles.size == 3
    }

    override fun setGraphs(figure: Figure) {
        // TODO(Create graph)
    }
}