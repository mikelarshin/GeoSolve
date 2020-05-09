package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.App
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.properties.Delegates

class Node(foundX: Float, foundY: Float) : Movable {

    var x: Float = foundX
        get() = App.systemCoordinate.transformationMethodX(field)
    var y: Float = foundY
        get() = App.systemCoordinate.transformationMethodY(field)

    var char by Delegates.notNull<Char>()
    override fun toString(): String = char.toString()

    var startLine: Line? = null
    var finalLine: Line? = null

    var startAngle: Angle? = null
    var centerAngle: Angle? = null
    var finalAngle: Angle? = null

    fun getConnectionList(): List<Element?> {
        return listOf(startLine, finalLine, startAngle, centerAngle, finalAngle)
    }

    override fun move(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun inRadius(x: Float, y: Float): Boolean {
        val radius = POINT_SIZE / 20
        val xBool = this.x - radius < x && x < this.x + radius

        val yBool = this.y - radius < y && y < this.y + radius

        return xBool && yBool
    }
}