package open.geosolve.geosolve.model.canvas.data.elements

import open.geosolve.geosolve.model.canvas.controllers.AllNodes
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.math.XY
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.view.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.POINT_SIZE

class Node(foundX: Float, foundY: Float) : Element, XY {

    override var x: Float = foundX
        get() = systemCoordinate.convertX(field)
    override var y: Float = foundY
        get() = systemCoordinate.convertY(field)

    var char = "Error"
    override fun toString(): String = char

    var circle: Circle? = null
    val angles: MutableSet<Angle> = mutableSetOf()
    val lines: MutableSet<Line> = mutableSetOf()

    val centerAngles
        get() = angles.filter { it.angleNode == this }

    // Bind
    var bind: Bind? = null
        set(value) {
            field?.bindNodes?.remove(this)
            value?.bindNodes?.add(this)
            field = value
            updateXYbyBind()
        }

    fun updateXYbyBind() {
        bind?.onBindNodeXY(this, XYPoint(x, y))
    }

    // Movable
    fun move(point: XYPoint) {
        lines.forEach { it.moveEvent() }
        circle?.moveEvent()

        if (bind == null) {
            this.x = point.x
            this.y = point.y
        } else
            bind?.onBindNodeXY(this, point)
    }

    // Element
    override fun remove() {
        lines.forEach { it.remove() }
        angles.forEach { it.remove() }
        circle?.remove()

        AllNodes.remove(this)
    }

    override fun inRadius(point: XYPoint): Boolean {
        val useTouchZone = POINT_SIZE / 30

        val xBool = this.x - useTouchZone < point.x && point.x < this.x + useTouchZone

        val yBool = this.y - useTouchZone < point.y && point.y < this.y + useTouchZone

        return xBool && yBool
    }
}