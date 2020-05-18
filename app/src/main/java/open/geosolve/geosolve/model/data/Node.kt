package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.generalized.Bind
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.Movable
import open.geosolve.geosolve.view.view.draw.PaintConstant.POINT_SIZE
import kotlin.properties.Delegates

class Node(foundX: Float, foundY: Float) : Movable, Element {

    var x: Float = foundX
        get() = App.systemCoordinate.convertX(field)
    var y: Float = foundY
        get() = App.systemCoordinate.convertY(field)

    var char by Delegates.notNull<Char>()
    override fun toString(): String = char.toString()

    var centerAngle: Angle? = null
    val neighborAngles: MutableList<Angle> = mutableListOf()
    val neighborLines: MutableList<Line> = mutableListOf()

    // Bind
    var bind: Bind? = null
        set(value) {
            field?.bindNodeList?.remove(this)
            value?.bindNodeList?.add(this)
            field = value
            updateXYbyBind()
        }

    fun updateXYbyBind() {
        bind?.toBindNodeXY(this, x, y)
    }

    // Movable
    override fun move(x: Float, y: Float) {
        if (bind != null)
            bind?.toBindNodeXY(this, x, y)
        else {
            this.x = x
            this.y = y
        }
    }

    // Element
    override fun delConnection() {
        for (element in  neighborLines + neighborAngles + centerAngle) {
            if (find == element)
                find = null
            (element as Element?)?.delConnection()
            (element as Element?)?.let { App.delElementFromFigure(it) }
        }
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val useTouchZone = POINT_SIZE / 30

        val xBool = this.x - useTouchZone < x && x < this.x + useTouchZone

        val yBool = this.y - useTouchZone < y && y < this.y + useTouchZone

        return xBool && yBool
    }
}