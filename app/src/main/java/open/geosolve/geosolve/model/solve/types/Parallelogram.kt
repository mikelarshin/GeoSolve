package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.SolveUtil.next
import open.geosolve.geosolve.model.solve.TypeFigure

object Parallelogram : TypeFigure(Quadrangle) {
    override fun isMatch(figure: Figure) =
        figure.mLines[0].getValue() == figure.mLines.next(0, 2).getValue()
     && figure.mLines[0].getValue() != null
     && figure.mLines[1].getValue() == figure.mLines.next(1, 2).getValue()
     && figure.mLines[1].getValue() != null
}