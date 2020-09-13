package open.geosolve.geosolve.model.canvas.controllers

import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.elements.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.math.XYPoint

object ElementGetter {
    // generalized getters
    fun getElement(point: XYPoint): Element? {

        getAngle(point)?.let { angle ->
            return angle
        }

        getNode(point)?.let { node ->
            return node
        }

        getCircle(point)?.let { circle ->
            return circle
        }

        getLine(point)?.let { line ->
            return line
        }

        return null
    }

    // getters
    private fun getNode(point: XYPoint): Node? {
        val nodes = AllNodes + AllCircles.map { it.centerNode } // все точки + точки из центра круга
        return getFromElementSet(nodes, point) as Node?
    }

    private fun getLine(point: XYPoint) = getFromElementSet(AllLines, point) as Line?

    private fun getAngle(point: XYPoint) = getFromElementSet(AllAngles, point) as Angle?

    private fun getCircle(point: XYPoint) = getFromElementSet(AllCircles, point) as Circle?

    private fun getFromElementSet(set: Set<Element>, point: XYPoint): Element? {
        return set.find { element -> element.inRadius(point) } // TODO(binary search)
    }
}