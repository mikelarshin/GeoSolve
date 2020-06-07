package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.model.solve.type.UnknownFigure

class Figure {

    val mNodes: MutableList<Node> = mutableListOf()
    val mLines: MutableList<Line> = mutableListOf()
    val mAngles: MutableList<Angle> = mutableListOf()
    var mCircle: Circle? = null

    var square: Int? = null // TODO(implement this)

    var typeFigure: SolveFigure = UnknownFigure
    var subTypeFigure: SolveFigure = UnknownFigure

    fun isComplete(): Boolean = isClose() || mCircle != null
    fun isClose(): Boolean = if (mLines.size != 0) mLines.first().startNode == mLines.last().finalNode else false
    fun isEmpty(): Boolean = mNodes.isEmpty() && mLines.isEmpty() && mAngles.isEmpty() && mCircle == null
    fun isNotEmpty(): Boolean = !isEmpty()

    // TODO(DELETE THIS DEBUGGER)
    override fun toString(): String {
        val typeFigureName = typeFigure::class.simpleName
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