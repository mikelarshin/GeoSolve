package open.geosolve.geosolve.model

import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

object DrawControl {

    fun delNode(touchX: Float, touchY: Float) {
        getNode(touchX, touchY)?.let { node ->
            for (element in node.getConnectionList()) {
                if (find == element)
                    find = null
                element?.delConnection()
                allAngles.remove(element)
                allLines.remove(element)
            }

            allNodes.remove(node)
        }
    }

    fun getNode(touchX: Float, touchY: Float): Node? {
        var outNode: Node? = null
        for (node in allNodes + allCircles.map { it.centerNode }) // TODO(сделать нормальное перемешение окружности)
            if (node.inRadius(touchX, touchY)) {
                outNode = node
                break
            }
        return outNode
    }

    private fun getLine(touchX: Float, touchY: Float): Line? {
        var outLine: Line? = null
        for (line in allLines)
            if (line.inRadius(touchX, touchY)) {
                outLine = line
                break
            }
        return outLine
    }

    fun getElement(x: Float, y: Float, unSelectableCallback: () -> Unit): Element? {

        getNode(x, y)?.let { node ->
            if (node.centerAngle != null)
                return node.centerAngle
            else
                unSelectableCallback()
        }

        getLine(x, y)?.let { line ->
            return line
        }

        return null
    }
}