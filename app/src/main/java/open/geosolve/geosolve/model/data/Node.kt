package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.AllNodes
import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.model.data.generalized.Bind
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.view.view.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.view.draw.PaintConstant.POINT_SIZE
import kotlin.properties.Delegates

class Node(foundX: Float, foundY: Float) : Element {

    var x: Float = foundX
        get() = systemCoordinate.convertX(field)
    var y: Float = foundY
        get() = systemCoordinate.convertY(field)

    var char by Delegates.notNull<String>()
    override fun toString(): String = char

    var circle: Circle? = null
    var centerAngle: Angle? = null
    val neighborAngles: MutableList<Angle> = mutableListOf()
    val neighborLines: MutableList<Line> = mutableListOf()

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
        if (neighborLines.isNotEmpty())
            neighborLines.forEach { it.moveEvent() }
        circle?.moveEvent()

        if (bind == null) {
            this.x = x
            this.y = y
        } else
            bind?.toBindNodeXY(this, x, y)
    }

    // Element
    override fun remove() {
        for (element in neighborLines + neighborAngles + centerAngle) {
            if (find == element)
                find = null
            (element as Element?)?.remove()
        }

        AllNodes.remove(this)
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val useTouchZone = POINT_SIZE / 30

        val xBool = this.x - useTouchZone < x && x < this.x + useTouchZone

        val yBool = this.y - useTouchZone < y && y < this.y + useTouchZone

        return xBool && yBool
    }

    operator fun component1() = x
    operator fun component2() = y
}