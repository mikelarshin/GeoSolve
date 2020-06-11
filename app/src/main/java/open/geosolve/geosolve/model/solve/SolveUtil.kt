package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.type.AngleFigure
import open.geosolve.geosolve.model.solve.type.Rectangle
import open.geosolve.geosolve.model.solve.type.Triangle
import open.geosolve.geosolve.model.solve.type.UnknownFigure

object SolveUtil {

    var typeSolve: SolveFigure = UnknownFigure
    var subTypeSolve: SolveFigure = UnknownFigure

    private fun setTypeSolve(figure: Figure) {
        typeSolve = when {
            Rectangle.isMatch(figure) -> Rectangle
            Triangle.isMatch(figure) -> Triangle
            AngleFigure.isMatch(figure) -> AngleFigure
            else -> UnknownFigure
        }
        subTypeSolve = UnknownFigure
    }

    fun solve(figure: Figure) {
        setTypeSolve(figure)

        zeroGraph(figure)

        typeSolve.setGraphs(figure)

        for (line in figure.lines)
            line.solve()

        for (angle in figure.angles)
            angle.solve()
    }

    private fun zeroGraph(figure: Figure) {
        figure.lines.map { it.onKnownFunList.clear() }
        figure.angles.map { it.onKnownFunList.clear() }
    }

    fun getSolveSteps(figure: Figure, solveCallback: SolveCallback) {

        val valuePresented = figure.find?.getValue() != null
        val isNoSolve = figure.find!!.whereFromValueList == null

        if (figure.find == null) {
            solveCallback.noSearchedElement()
        } else if (valuePresented && isNoSolve) {
            solveCallback.userValue()
        } else if (isNoSolve) {
            solveCallback.notFound()
        } else {
            solveCallback.found(getList(figure.find!!).reversed() + listOf(figure.find!!))
        }
    }

    private fun getList(
        found: Element,
        stepList: MutableList<Element> = mutableListOf()
    ): List<Element> {
        if (found.whereFromValueList == null)
            return listOf() // dead end graph

        for (element in found.whereFromValueList!!) {
            val addStepList = getList(element, stepList)
            for (addStep in addStepList) {
                if (stepList.contains(addStep))
                    stepList.remove(addStep)
                stepList += addStep
            }
        }

        if (stepList.contains(found))
            return stepList
        return listOf(found) + stepList
    }
}
