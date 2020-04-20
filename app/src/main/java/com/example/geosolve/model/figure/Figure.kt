package com.example.geosolve.model.figure

import java.util.*

class Figure {
    var find: Any? = null
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

    fun addLine(startNode: Node, finNode: Node) {
        mLines.add(Line(startNode, finNode))
    }

    fun delNode(touchX: Float, touchY: Float) {
        for (node in mNodes)
            if (node.inRadius(touchX, touchY)) {
                val index: Int = mNodes.indexOf(node)
                for (line in node.neighborLines) {
                    mLines.remove(line)
                    if (find == line)
                        find = null
                }

                mNodes.removeAt(index)
                break
            }
    }

    fun clearFigure() {
        mNodes.clear()
        mLines.clear()
        mAngles.clear()
        find = null
    }

    fun getInRadius(x: Float, y: Float): Any? {
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

        return returnElem
    }
}