package open.geosolve.geosolve.model.canvas

import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Circle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Element

object ElementGetter {
    // generalized getters
    fun getElement(x: Float, y: Float): Element? {

        getAngle(x, y)?.let { angle ->
            return angle
        }

        getNode(x, y)?.let { node ->
            return node
        }

        getCircle(x, y)?.let { circle ->
            return circle
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