package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object Quadrangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean =
        figure.mNodes.size == 4
            && figure.mLines.size == 4
            && figure.mAngles.size == 4

    override fun setGraphs(figure: Figure) {
        // TODO("Not yet implemented")
    }

    override fun setSubType(figure: Figure) {
        figure.subTypeSolve = when {
            Rectangle.isMatch(figure) -> Rectangle
            else -> UnknownFigure
        }
    }
}