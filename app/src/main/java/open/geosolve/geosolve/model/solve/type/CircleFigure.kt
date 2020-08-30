package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.canvas.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object CircleFigure : SolveFigure {
    override fun isMatch(figure: Figure) =
        (figure.mNodes.isEmpty()
                && figure.mLines.isEmpty()
                && figure.mAngles.isEmpty())
                && figure.mCircle != null
}