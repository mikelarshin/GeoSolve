package open.geosolve.geosolve.model.canvas.data.containers

import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.elements.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.XYPoint
import java.io.Serializable

class CanvasData : Serializable {
    var lastNode: Node? = null

    init {
        makeActive()
    }
    companion object {
        lateinit var activeCanvasData: CanvasData

        var find
            get() = activeCanvasData.find
            set(value) { activeCanvasData.find = value }

        val activeFigureList get() = activeCanvasData.figureList
        val activeFigure get() = activeFigureList.last()

        val allNodes get() = activeCanvasData.allNodes
        val allLines get() = activeCanvasData.allLines
        val allAngles get() = activeCanvasData.allAngles
        val allCircles get() = activeCanvasData.allCircles
    }

    fun makeActive() {
        activeCanvasData = this
    }

    var find: SolveGraph? = null

    val figureList = FigureSet()

    val allNodes = NodeSet(figureList)
        get() = field.apply { update() }

    val allLines = LineSet(figureList)
        get() = field.apply { update() }

    val allAngles = AngleSet(figureList)
        get() = field.apply { update() }

    val allCircles = CircleSet(figureList)
        get() = field.apply { update() }

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
        val nodes = allNodes + allCircles.map { it.centerNode } // все точки + точки из центра круга
        return getFromElementSet(nodes, point) as Node?
    }

    private fun getLine(point: XYPoint) = getFromElementSet(allLines, point) as Line?

    private fun getAngle(point: XYPoint) = getFromElementSet(allAngles, point) as Angle?

    private fun getCircle(point: XYPoint) = getFromElementSet(allCircles, point) as Circle?

    private fun getFromElementSet(set: Set<Element>, point: XYPoint): Element? {
        return set.find { element -> element.inRadius(point) } // TODO(binary search)
    }
}