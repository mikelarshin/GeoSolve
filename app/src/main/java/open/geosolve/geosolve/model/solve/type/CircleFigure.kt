package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object CircleFigure : SolveFigure {
    override fun isMatch(figure: Figure) =
        (figure.mNodes.isEmpty() && figure.mLines.isEmpty() && figure.mAngles.isEmpty())
                && figure.mCircle != null
    override fun setGraphs(figure: Figure) {
        // TODO("Not yet implemented")
    }
    override fun setSubType(figure: Figure) {}
}