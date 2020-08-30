package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.canvas.*
import open.geosolve.geosolve.model.canvas.data.Figure
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.type.*

object SolveUtil {

    private fun setTypeSolve(figure: Figure) {
        figure.typeSolve = when {
            CircleFigure.isMatch(figure) -> CircleFigure
            Quadrangle.isMatch(figure) -> Quadrangle
            Triangle.isMatch(figure) -> Triangle
            AngleFigure.isMatch(figure) -> AngleFigure
            else -> UnknownFigure
        }
        figure.typeSolve.setSubType(figure)
    }

    fun solveGraphs() {
        for (figure in FigureList) {
            setTypeSolve(figure)
            zeroGraph(figure)
            figure.typeSolve.setGraphs(figure)
            figure.subTypeSolve.setGraphs(figure)
        }

        for (element in (AllLines + AllAngles + AllCircles))
            (element as SolveGraph).solve()
    }

    private fun zeroGraph(figure: Figure) {
        figure.mLines.map { it.onKnownFunctions.clear() }
        figure.mAngles.map { it.onKnownFunctions.clear() }
    }

    fun showStepSolveList(callback: CallBackSolveUi) {
        when {
            find == null -> {
                callback.findNotMark()
                return
            }
            find?.getValue() != null && find!!.whereFromValueList == null -> {
                callback.userInputValue()
                return
            }
            find!!.whereFromValueList == null -> {
                callback.solveIsNotFound()
                return
            }
        }

        val solveList = getList(find!!).reversed() + listOf(find!!)

        callback.solveIsFound(solveList)
    }

    private fun getList(found: SolveGraph, stepList: MutableList<SolveGraph> = mutableListOf()): List<SolveGraph> {
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
