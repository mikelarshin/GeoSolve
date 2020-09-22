package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.TypeFigure

object AnyFigure : TypeFigure(null) { // AnyFigure haven't parent Type
    override fun isMatch(figure: Figure) = true

    init { // initialization all childs
        Polygon
        Quadrangle
        Parallelogram
        Rectangle
        Triangle
        RightTriangle
        CircleFigure
        AngleFigure
    }
}