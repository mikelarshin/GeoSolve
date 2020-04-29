package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.type.AngleFigure
import open.geosolve.geosolve.model.solve.type.Rectangle
import open.geosolve.geosolve.model.solve.type.Triangle
import open.geosolve.geosolve.model.solve.type.UnknownFigure

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

        zeroGraph(figure)

        typeSolve.setGraphs(figure)

        for (line in figure.mLines)
            line.solve()

        for (angle in figure.mAngles)
            angle.solve()
    }
    
    private fun zeroGraph(figure: Figure){
        for (line in figure.mLines)
            line.onKnownFun = {}

        for (angle in figure.mAngles)
            angle.onKnownFun = {}
    }

    fun getStepSolveList(): List<StepSolve> {
        return emptyList()
    }
}