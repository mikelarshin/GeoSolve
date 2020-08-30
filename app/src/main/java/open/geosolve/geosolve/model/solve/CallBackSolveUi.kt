package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph

interface CallBackSolveUi {
    fun findNotMark()
    fun solveIsNotFound()
    fun userInputValue()
    fun solveIsFound(solveList: List<SolveGraph>)
}