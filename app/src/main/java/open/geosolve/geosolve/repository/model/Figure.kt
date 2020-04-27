package open.geosolve.geosolve.repository.model

import java.util.*

class Figure {

    var find: Element? = null
    val mNodes: MutableList<Node> = ArrayList()
    val mLines: MutableList<Line> = ArrayList()
    val mAngles: MutableList<Angle> = ArrayList()

    fun stopAllNode(): Boolean {
        var answer = false
        for (node in mNodes) {
            answer = answer or node.stopMove()
        }
        return answer
    }

    fun addNode(node: Node) {
        mNodes.add(node)
    }

    fun addLine(startNode: Node, finalNode: Node) {
        val line = Line(startNode, finalNode)
        mLines.add(line)

        startNode.neighborLines.add(line)
        finalNode.neighborLines.add(line)
    }

    fun addAngle(startNode: Node, angleNode: Node, finalNode: Node){
        val angle = Angle(startNode, angleNode, finalNode)
        mAngles.add(angle)

        angleNode.neighborAngles.add(angle)
    }

    fun delNode(touchX: Float, touchY: Float) {
        for (node in mNodes)
            if (node.inRadius(touchX, touchY)) {
                for (line in node.neighborLines)
                    mLines.remove(line)

                for (angle in node.neighborAngles)
                    mAngles.remove(angle)

                mNodes.remove(node)
                break
            }
    }

    fun clearFigure() {
        mNodes.clear()
        mLines.clear()
        mAngles.clear()
        find = null
    }

    fun getInRadius(x: Float, y: Float): Element? {
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

        if (returnElem is Node)
            if (returnElem.neighborAngles.size > 0)
                return returnElem.neighborAngles[0]
        return returnElem as Element?
    }
}