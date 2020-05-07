package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object AngleFigure : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.nodes.size == 3 && figure.angles.size == 1 && figure.lines.size == 2
    }

    override fun setGraphs(figure: Figure) {
        // TODO(create graph)
    }
}