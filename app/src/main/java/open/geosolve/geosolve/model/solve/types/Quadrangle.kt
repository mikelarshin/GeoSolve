package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.TypeFigure

object Quadrangle : TypeFigure(Polygon) {
    override fun isMatch(figure: Figure): Boolean =
        figure.mNodes.size == 4
            && figure.mLines.size == 4
            && figure.mAngles.size == 4
}