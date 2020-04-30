package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.type.AngleFigure
import open.geosolve.geosolve.model.solve.type.Rectangle
import open.geosolve.geosolve.model.solve.type.Triangle
import open.geosolve.geosolve.model.solve.type.UnknownFigure
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil

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
        figure.mLines.map { it.zeroing() }
        figure.mAngles.map { it.zeroing() }
    }

    fun getStepSolveList(): List<DesignUtil> {
        return emptyList()
    }
}