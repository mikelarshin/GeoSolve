package open.geosolve.geosolve.model.solve

import android.util.Log
import open.geosolve.geosolve.model.CallBackSolveUi
import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.type.AngleFigure
import open.geosolve.geosolve.model.solve.type.Rectangle
import open.geosolve.geosolve.model.solve.type.Triangle
import open.geosolve.geosolve.model.solve.type.UnknownFigure
import open.geosolve.geosolve.view.screens.solveScreen.RecycleAdapter

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
        figure.mLines.map { it.onKnownFun = {} }
        figure.mAngles.map { it.onKnownFun = {} }
    }

    fun showStepSolveList(figure: Figure, callbackUi: CallBackSolveUi){
        when {
            figure.find == null -> {
                callbackUi.findNotMark()
                return
            }
            figure.find!!.whereFromValueList == null -> {
                callbackUi.solveIsNotFound()
                return
            }
        }

        RecycleAdapter.addAll(getList(figure.find!!))
        callbackUi.solveIsFound()
    }

    private fun getList(found: Element, stepList: MutableList<Element> = mutableListOf()): List<Element>{
        if (found.whereFromValueList == null)
            return stepList

        for(element in found.whereFromValueList!!)
            stepList += getList(element, stepList)

        return listOf(found) + stepList
    }
}
