package open.geosolve.geosolve.model.canvas.data

import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.model.solve.type.UnknownFigure

class Figure {

    val mNodes: MutableSet<Node> = linkedSetOf()
    val mLines: MutableList<Line> = mutableListOf()
    val mAngles: MutableList<Angle> = mutableListOf()
    var mCircle: Circle? = null

    var square: Int? = null // TODO(implement square)
    var perimeter: Int? = null // TODO(implement perimeter)

    var typeSolve: SolveFigure = UnknownFigure
    var subTypeSolve: SolveFigure = UnknownFigure

    fun isComplete() = isClose() || mCircle != null
    fun isClose() = mLines.size != 0 && mLines.first().firstNode == mLines.last().secondNode // TODO(rewrite this)
    fun isEmpty() = mNodes.isEmpty() && mLines.isEmpty() && mAngles.isEmpty() && mCircle == null
    fun isNotEmpty() = !isEmpty()

    fun contains(element: Element) =
                mNodes .contains(element) ||
                mLines .contains(element) ||
                mAngles.contains(element) ||
                mCircle == element


    // TODO(DELETE THIS DEBUGGER)
    override fun toString(): String {
        val typeFigureName = typeSolve::class.simpleName
//        val subTypeFigureName = subTypeFigure::class.simpleName

        return "$typeFigureName\n" +
                when {
                    isEmpty() -> ""

                    mCircle == null -> "Nodes: ${mNodes.joinToString { "$it" }}   \n" +
                            "Lines: ${mLines.joinToString { "$it" }}   \n" +
                            "Angles: ${mAngles.joinToString { "$it" }}"

                    else -> "Circle"
                }

    }
}