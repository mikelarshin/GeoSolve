package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeCanvasData
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.find
import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.types.*

object SolveUtil {

    private fun Figure.setType() {
        while (typesDeque.last().childTypes.isNotEmpty()) {
            val childSet = typesDeque.last().childTypes

            val figureChild = childSet.find { child -> child.isMatch(this) }

            if (figureChild != null)
                typesDeque.add(figureChild)
            else
                break
        }
    }

    fun solveGraphs() {
        for (figure in activeCanvasData.figureList) {
            figure.clearGraph()
            figure.setType()
            figure.typesDeque.forEach {
                it.setGraphs(figure)
            }
        }

        for (element in (activeCanvasData.allLines + activeCanvasData.allAngles + activeCanvasData.allCircles))
            (element as SolveGraph).solve()
    }

    private fun Figure.clearGraph() {
        typesDeque.clear()
        typesDeque.add(AnyFigure)

        mLines.map { it.onKnownFunctions.clear() }
        mAngles.map { it.onKnownFunctions.clear() }
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

    fun List<SolveGraph>.next(from: Int, to: Int = 1) = this[(from + to) % this.size]
}
