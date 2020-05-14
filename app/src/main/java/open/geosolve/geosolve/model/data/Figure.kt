package open.geosolve.geosolve.model.data

class Figure {

    val mNodes: MutableList<Node> = mutableListOf()
    val mLines: MutableList<Line> = mutableListOf()
    val mAngles: MutableList<Angle> = mutableListOf()
    var mCircle: Circle? = null

    fun isComplete(): Boolean = isClose() || mCircle != null
    fun isClose(): Boolean = if (mLines.size != 0) mLines.first().startNode == mLines.last().finalNode else false
    fun isEmpty(): Boolean = mNodes.isEmpty() || mLines.isEmpty() || mAngles.isEmpty() || mCircle == null

    // TODO(DELETE THIS DEBUGGER)
    override fun toString(): String {
        return "Nodes: ${mNodes.size} \n" +
                "Lines: ${mLines.size} \n" +
                "Angles: ${mAngles.size} \n"
    }
}