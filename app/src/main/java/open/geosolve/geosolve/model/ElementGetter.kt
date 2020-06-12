package open.geosolve.geosolve.model

import open.geosolve.geosolve.AllAngles
import open.geosolve.geosolve.AllCircles
import open.geosolve.geosolve.AllLines
import open.geosolve.geosolve.AllNodes
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.data.generalized.Element

object ElementGetter {
    // generalized getters
    fun getElement(x: Float, y: Float): Element? {

        getAngle(x, y)?.let { angle ->
            return angle
        }

        getCircle(x, y)?.let { circle ->
            return circle
        }

        getNode(x, y)?.let { node ->
            return node
        }

        getLine(x, y)?.let { line ->
            return line
        }

        return null
    }

    // getters
    private fun getNode(touchX: Float, touchY: Float): Node? {
        for (node in AllNodes + AllCircles.map { it.centerNode }) // все точки + точки из центра круга
            if (node.inRadius(touchX, touchY))
                return node
        return null
    }

    private fun getLine(touchX: Float, touchY: Float): Line? {
        for (line in AllLines)
            if (line.inRadius(touchX, touchY))
                return line
        return null
    }

    private fun getAngle(touchX: Float, touchY: Float): Angle? {
        for (angle in AllAngles)
            if (angle.inRadius(touchX, touchY))
                return angle
        return null
    }

    private fun getCircle(touchX: Float, touchY: Float): Circle? {
        for (circle in AllCircles)
            if (circle.inRadius(touchX, touchY))
                return circle
        return null
    }
}