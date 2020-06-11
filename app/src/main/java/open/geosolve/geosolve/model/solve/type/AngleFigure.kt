package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object AngleFigure : SolveFigure {
    override fun isMatch(figure: Figure): Boolean =
        figure.mNodes.size == 3
                && figure.mAngles.size == 1
                && figure.mLines.size == 2


    override fun setGraphs(figure: Figure) {
        // TODO("Not yet implemented")
    }
    override fun setSubType(figure: Figure) {}
}