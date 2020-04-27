package open.geosolve.geosolve.repository.solve.type

import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.solve.SolveFigure

object AngleFigure : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 3 && figure.mAngles.size == 1 && figure.mLines.size == 2
    }

    override fun setGraphs(figure: Figure) {
        // TODO(create graph)
    }
}