package open.geosolve.geosolve.model.data

class Figure {

    var find: Element? = null
    val mNodes: MutableList<Node> = mutableListOf()
    val mLines: MutableList<Line> = mutableListOf()
    val mAngles: MutableList<Angle> = mutableListOf()

    fun isClose(): Boolean = if (mLines.size != 0) mLines.first().startNode == mLines.last().finalNode else false

    // TODO(DELETE THIS DEBUGGER)
    override fun toString(): String {
        return "Nodes: ${mNodes.size} \n" +
                "Lines: ${mLines.size} \n" +
                "Angles: ${mAngles.size} \n"
    }
}