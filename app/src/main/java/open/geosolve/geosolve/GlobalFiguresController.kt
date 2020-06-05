package open.geosolve.geosolve

import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object GlobalFiguresController {
    var figureList: MutableList<Figure> = mutableListOf(Figure())
    var find: SolveGraph? = null

    val allNodes: Set<Node>
        get() = figureList.flatMap { it.mNodes }.toSet()
    val allLines: Set<Line>
        get() = figureList.flatMap { it.mLines }.toSet()
    val allAngles: Set<Angle>
        get() = figureList.flatMap { it.mAngles }.toSet()
    val allCircles: Set<Circle>
        get() = figureList.flatMap { listOf(it.mCircle) }.filterNotNull().toSet()

    fun removeElementGlobal(element: Element) {
        for (figure in figureList) {
            if (figure.mLines.contains(element))
                figure.mLines.remove(element)
            if (figure.mAngles.contains(element))
                figure.mAngles.remove(element)
            if (figure.mNodes.contains(element))
                figure.mNodes.remove(element)
            if (figure.mCircle == element)
                figure.mCircle = null
        }
    }
}