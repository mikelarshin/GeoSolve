package open.geosolve.geosolve.model

import open.geosolve.geosolve.GlobalFiguresController.allCircles
import open.geosolve.geosolve.GlobalFiguresController.allLines
import open.geosolve.geosolve.GlobalFiguresController.allNodes
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.Movable
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object ElementGetter {
    // getters
    private fun getNode(touchX: Float, touchY: Float): Node? {
        for (node in allNodes + allCircles.map { it.centerNode }) // все точки + точки из центра круга
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

    private fun getAngle(touchX: Float, touchY: Float): Angle? {
        return getNode(touchX, touchY)?.centerAngle
//        for (angle in allAngles) TODO(implement angle.inRadius)
//            if (angle.inRadius(touchX, touchY))
//                return angle
//        return null
    }

    private fun getCircle(touchX: Float, touchY: Float): Circle? {
        for (circle in allCircles)
            if (circle.inRadius(touchX, touchY))
                return circle
        return null
    }

    // generalized getters
    fun getDeletableElement(x: Float, y: Float): Element? {
        getCircle(x, y)?.let { circle ->
            return circle
        }

        getNode(x, y)?.let { node ->
            return node
        }

        return null
    }

    fun getGraphElement(x: Float, y: Float): SolveGraph? {

        getAngle(x, y)?.let { angle ->
            return angle
        }

        getCircle(x, y)?.let { circle ->
            return circle
        }

        getLine(x, y)?.let { line ->
            return line
        }

        return null
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
}