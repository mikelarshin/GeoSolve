package open.geosolve.geosolve.repository.solve

import open.geosolve.geosolve.repository.UnwindCallback
import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.model.StepSolve
import open.geosolve.geosolve.repository.solve.type.AngleFigure
import open.geosolve.geosolve.repository.solve.type.Rectangle
import open.geosolve.geosolve.repository.solve.type.Triangle
import open.geosolve.geosolve.repository.solve.type.UnknownFigure

object SolveUtil {

    var typeSolve: SolveFigure =
        UnknownFigure

    private fun setTypeSolve(figure: Figure) {
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

    private fun zeroGraph(figure: Figure) {
        for (line in figure.mLines)
            line.onKnownFun = {}

        for (angle in figure.mAngles)
            angle.onKnownFun = {}
    }

    fun unwindTree(figure: Figure, callback: UnwindCallback): List<StepSolve>? {
        if (figure.find == null) {
            callback.emptyElement()
            return null
        }

        if (figure.find!!.whereFromValueList.isEmpty()) {
            callback.emptyStackCallback()
            return null
        }

        figure.find!!.whereFromValueList.forEach {

        }
    }
}