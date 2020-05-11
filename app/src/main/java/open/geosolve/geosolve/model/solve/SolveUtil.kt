package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.type.AngleFigure
import open.geosolve.geosolve.model.solve.type.Rectangle
import open.geosolve.geosolve.model.solve.type.Triangle
import open.geosolve.geosolve.model.solve.type.UnknownFigure
import open.geosolve.geosolve.view.screens.solveScreen.RecycleAdapter

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

    fun solve(figureList: List<Figure>) {
        for (figure in figureList) {
            setTypeSolve(figure)
            zeroGraph(figure)
            typeSolve.setGraphs(figure)
        }

        for (element in allLines + allAngles + allCircles)
            element.solve()
    }

    private fun zeroGraph(figure: Figure) {
        figure.mLines.map { it.onKnownFunList.clear() }
        figure.mAngles.map { it.onKnownFunList.clear() }
    }

    fun showStepSolveList(figure: Figure, callbackUi: CallBackSolveUi) {
        when {
            find == null -> {
                callbackUi.findNotMark()
                return
            }
            find?.getValue() != null && find!!.whereFromValueList == null -> {
                callbackUi.userInputValue()
                return
            }
            find!!.whereFromValueList == null -> {
                callbackUi.solveIsNotFound()
                return
            }
        }

        val list = getList(find!!).reversed() + listOf(find!!)

        callbackUi.solveIsFound(list)
    }

    private fun getList(found: Element, stepList: MutableList<Element> = mutableListOf()): List<Element> {
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
