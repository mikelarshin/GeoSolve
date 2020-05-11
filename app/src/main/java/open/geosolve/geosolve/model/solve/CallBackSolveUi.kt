package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.data.Element

interface CallBackSolveUi {
    fun findNotMark()
    fun solveIsNotFound()
    fun userInputValue()
    fun solveIsFound(list: List<Element>)
}