package open.geosolve.geosolve.repository.solve

import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.solve.type.AngleFigure
import open.geosolve.geosolve.repository.solve.type.Rectangle
import open.geosolve.geosolve.repository.solve.type.Triangle
import open.geosolve.geosolve.repository.solve.type.UnknownFigure

object SolveUtil {

    var typeSolve: SolveFigure =
        UnknownFigure

    private fun setTypeSolve(figure: Figure){
        typeSolve = when {
            Rectangle.isMatch(figure) -> Rectangle
            Triangle.isMatch(figure) -> Triangle
            AngleFigure.isMatch(figure) -> AngleFigure
            else -> UnknownFigure
        }
    }

    fun solve(figure: Figure) {
        setTypeSolve(figure)

        typeSolve.setGraphs(figure)

        for (line in figure.mLines)
            line.solve()

        for (angle in figure.mAngles)
            angle.solve()
    }
}