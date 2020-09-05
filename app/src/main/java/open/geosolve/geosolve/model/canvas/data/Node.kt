package open.geosolve.geosolve.model.canvas.data

import open.geosolve.geosolve.model.canvas.AllNodes
import open.geosolve.geosolve.model.canvas.data.generalized.Bind
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.math.XY
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.POINT_SIZE

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
        bind?.toBindNodeXY(this, x, y)
    }

    // Movable
    fun move(x: Float, y: Float) {
        lines.forEach { it.moveEvent() }
        circle?.moveEvent()

        if (bind == null) {
            this.x = x
            this.y = y
        } else
            bind?.toBindNodeXY(this, x, y)
    }

    // Element
    override fun remove() {
        lines.forEach { it.remove() }
        angles.forEach { it.remove() }
        circle?.remove()

        AllNodes.remove(this)
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val useTouchZone = POINT_SIZE / 30

        val xBool = this.x - useTouchZone < x && x < this.x + useTouchZone

        val yBool = this.y - useTouchZone < y && y < this.y + useTouchZone

        return xBool && yBool
    }
}