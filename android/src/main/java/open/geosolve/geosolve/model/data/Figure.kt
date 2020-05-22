package open.geosolve.geosolve.model.data

import java.util.*

/*
 * TODO(DOC) Документировать хранение фигуры в классе
 */

class Figure {

    val isClose: Boolean get() = if (lines.size != 0) lines.first().startNode == lines.last().finalNode else false

    var find: Element? = null
    val nodes: MutableList<Node> = ArrayList()
    val lines: MutableList<Line> = ArrayList()
    val angles: MutableList<Angle> = ArrayList()

    fun clear() {
        find = null
        nodes.clear()
        lines.clear()
        angles.clear()
    }

    override fun toString(): String {
        return "Nodes: ${nodes.size} \n" +
                "Lines: ${lines.size} \n" +
                "Angles: ${angles.size} \n\n"
    }
}