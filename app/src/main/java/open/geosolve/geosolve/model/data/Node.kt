package open.geosolve.geosolve.model.data

import android.util.Log
import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.model.data.generalized.*
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.properties.Delegates

class Node(foundX: Float, foundY: Float) : Movable, Element {

    var x: Float = foundX
        get() = App.systemCoordinate.convertX(field)
    var y: Float = foundY
        get() = App.systemCoordinate.convertY(field)

    var char by Delegates.notNull<Char>()
    override fun toString(): String = char.toString()

    var startLine: Line? = null
    var finalLine: Line? = null

    var startAngle: Angle? = null
    var centerAngle: Angle? = null
    var finalAngle: Angle? = null

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
        allNodes.forEach { it.updateXYbyBind() }
    }

    // Element
    override fun delConnection() {
        for (element in listOf(startLine, finalLine, startAngle, centerAngle, finalAngle)) {
            if (App.find == element)
                App.find = null
            (element as Element?)?.delConnection()
            (element as Element?)?.let { App.delElement(it) }
        }
    }

    override fun inRadius(x: Float, y: Float): Boolean {
        val radius = POINT_SIZE / 20

        val xBool = this.x - radius < x && x < this.x + radius

        val yBool = this.y - radius < y && y < this.y + radius

        return xBool && yBool
    }
}