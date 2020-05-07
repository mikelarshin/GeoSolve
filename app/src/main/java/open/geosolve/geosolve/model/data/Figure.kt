package open.geosolve.geosolve.model.data

import java.util.*

/*
 * TODO(DOC) Документировать хранение фигуры в классе
 */

class Figure {

    var figureClosed = false

    var find: Element? = null
    val nodes: MutableList<Node> = ArrayList()
    val lines: MutableList<Line> = ArrayList()
    val angles: MutableList<Angle> = ArrayList()

    fun clear() {
        figureClosed = false

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