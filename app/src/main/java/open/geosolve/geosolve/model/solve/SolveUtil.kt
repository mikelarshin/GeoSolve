package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.type.*

object SolveUtil {

    private fun setTypeSolve(figure: Figure) {
        figure.typeFigure = when {
            CircleFigure.isMatch(figure) -> CircleFigure
            Quadrangle.isMatch(figure) -> Quadrangle
            Triangle.isMatch(figure) -> Triangle
            AngleFigure.isMatch(figure) -> AngleFigure
            else -> UnknownFigure
        }
        figure.typeFigure.setSubType(figure)
    }

    fun solve(figureList: List<Figure>) {
        for (figure in figureList) {
            setTypeSolve(figure)
            zeroGraph(figure)
            figure.typeFigure.setGraphs(figure)
            figure.subTypeFigure.setGraphs(figure)
        }

        for (element in (allLines + allAngles + allCircles))
            (element as SolveGraph).solve()
    }

    private fun zeroGraph(figure: Figure) {
        figure.mLines.map { it.onKnownFunList.clear() }
        figure.mAngles.map { it.onKnownFunList.clear() }
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

        val list = getList(find!!).reversed() + listOf(find!!)

        callback.solveIsFound(list)
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
