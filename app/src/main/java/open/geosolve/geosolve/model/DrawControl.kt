package open.geosolve.geosolve.model

import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.*

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

    private fun getNode(touchX: Float, touchY: Float): Node? {
        for (node in allNodes + allCircles.map { it.centerNode })
            if (node.inRadius(touchX, touchY))
                return node
        return null
    }

    private fun getLine(touchX: Float, touchY: Float): Line? {
        for (line in allLines)
            if (line.inRadius(touchX, touchY))
                return line
        return null
    }

    private fun getCircleByLine(touchX: Float, touchY: Float): Circle? {
        for (circle in allCircles)
            if (circle.inRadiusLine(touchX, touchY))
                return circle
        return null
    }

    fun getMovable(x: Float, y: Float): Movable? {
        getNode(x, y)?.let { node ->
            return node
        }

        getCircleByLine(x, y)?.let { circle ->
            return circle
        }

        return null
    }

    fun getElement(x: Float, y: Float, unSelectableCallback: () -> Unit): Element? {

        getNode(x, y)?.let { node ->
            if (node.centerAngle != null)
                return node.centerAngle
            else
                unSelectableCallback()
        }

        getCircleByLine(x, y)?.let{ circle ->
            return circle
        }

        getLine(x, y)?.let { line ->
            return line
        }

        return null
    }
}