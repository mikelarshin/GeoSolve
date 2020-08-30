package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.canvas.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object AngleFigure : SolveFigure {
    override fun isMatch(figure: Figure): Boolean =
        figure.mNodes.size == 3
                && figure.mAngles.size == 1
                && figure.mLines.size == 2
}