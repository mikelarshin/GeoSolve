package open.geosolve.geosolve.model.data

import java.util.*

class Figure {

    var figureClosed = false
    var find: Element? = null
    val mNodes: MutableList<Node> = ArrayList()
    val mLines: MutableList<Line> = ArrayList()
    val mAngles: MutableList<Angle> = ArrayList()

    fun addNode(node: Node) {
        mNodes.add(node)
    }

    fun addLine(startNode: Node, finalNode: Node) {
        val line = Line(startNode, finalNode)
        mLines.add(line)

        startNode.finalLine = line
        finalNode.startLine = line
    }

    fun addAngle(startLine: Line, finalLine: Line) {
        val angle = Angle(startLine, finalLine)
        mAngles.add(angle)

        startLine.startNode.startAngle = angle
        startLine.finalNode.centerAngle = angle
        finalLine.finalNode.finalAngle = angle
    }

    fun findNode(x: Float, y: Float): Node? {
        mNodes.forEach { node ->
            if (node.inRadius(x, y)) {
                return node
            }
        }

        return null
    }

    fun delNode(x: Float, y: Float) {
        findNode(x, y)?.let { delNode(it) }
    }

    fun delNode(node: Node) {
        for (angle in listOf(node.startAngle, node.centerAngle, node.finalAngle)) {
            if (find == angle)
                find = null
            mAngles.remove(angle)
        }

        for (line in listOf(node.startLine, node.finalLine)) {
            if (find == line)
                find = null
            mLines.remove(line)
        }
        node.delConnection()
        mNodes.remove(node)
    }

    fun clearFigure() {
        mNodes.clear()
        mLines.clear()
        mAngles.clear()
        find = null
        figureClosed = false
    }

    fun getInRadius(x: Float, y: Float, callback: () -> Unit): Element? {
        var returnElem: Any? = null

        for (line in mLines) {
            if (line.inRadius(x, y)) {
                returnElem = line
                break
            }
        }
        for (node in mNodes) {
            if (node.inRadius(x, y)) {
                returnElem = node
                break
            }
        }

        return when (returnElem) {
            is Node -> {
                if (returnElem.centerAngle == null) callback()
                return returnElem.centerAngle
            }
            is Line -> returnElem
            else -> null
        }
    }

    // TODO(DELETE THIS DEBUGGER)
    override fun toString(): String {
        return "Nodes: ${mNodes.size} \n" +
                "Lines: ${mLines.size} \n" +
                "Angles: ${mAngles.size} \n\n" +
                "x: ${if (mNodes.isNotEmpty()) mNodes[0].x else ""} \n" +
                "y: ${if (mNodes.isNotEmpty()) mNodes[0].y else ""} \n"
        return ""
    }
}