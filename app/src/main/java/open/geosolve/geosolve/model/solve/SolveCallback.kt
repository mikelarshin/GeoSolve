package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.data.Element

interface SolveCallback {
    fun noSearchedElement()
    fun notFound()
    fun userValue()
    fun found(solveSteps: List<Element>)
}