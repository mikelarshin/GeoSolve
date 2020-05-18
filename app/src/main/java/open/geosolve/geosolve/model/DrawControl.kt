package open.geosolve.geosolve.model

import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.figureList
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.data.generalized.Movable

object DrawControl {

    private fun getNode(touchX: Float, touchY: Float): Node? {
        for (node in allNodes + allCircles.map { it.centerNode })
            if (node.inRadius(touchX, touchY))
                return node
        return null
    }

    private fun getAngle(touchX: Float, touchY: Float): Angle? {
        return getNode(touchX, touchY)?.centerAngle
//        for (angle in allAngles)
//            if (angle.inRadius(touchX, touchY))
//                return angle
//        return null
    }

    private fun getLine(touchX: Float, touchY: Float): Line? {
        for (line in allLines)
            if (line.inRadius(touchX, touchY))
                return line
        return null
    }

    private fun getCircle(touchX: Float, touchY: Float): Circle? {
        for (circle in allCircles)
            if (circle.inRadius(touchX, touchY))
                return circle
        return null
    }

    fun delNode(touchX: Float, touchY: Float) {
        getNode(touchX, touchY)?.let { node ->
            node.delConnection()
            App.delElement(node)
        }
    }

    fun getMovable(x: Float, y: Float): Movable? {
        getNode(x, y)?.let { node ->
            return node
        }

        getCircle(x, y)?.let { circle ->
            return circle
        }

        return null
    }

    fun getGraphElement(x: Float, y: Float): SolveGraph? {

        getAngle(x, y)?.let { angle ->
            return angle
        }

        getLine(x, y)?.let { line ->
            return line
        }

        getCircle(x, y)?.let { circle ->
            return circle
        }

        return null
    }
}