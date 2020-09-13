package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object CircleFigure : SolveFigure {
    override fun isMatch(figure: Figure) =
        (figure.mNodes.isEmpty()
                && figure.mLines.isEmpty()
                && figure.mAngles.isEmpty())
                && figure.mCircle != null
}