package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.App
import kotlin.properties.Delegates

class Node(foundX: Float, foundY: Float) {

    var x: Float = foundX
        get() = App.systemCoordinate.transformationMethodX(field)
    var y: Float = foundY
        get() = App.systemCoordinate.transformationMethodY(field)

    var char by Delegates.notNull<Char>()

    var startLine: Line? = null
    var finalLine: Line? = null

    var startAngle: Angle? = null
    var centerAngle: Angle? = null
    var finalAngle: Angle? = null

    fun delConnection(){
        startLine?.delConnection()
        finalLine?.delConnection()

        startAngle?.delConnection()
        centerAngle?.delConnection()
        finalAngle?.delConnection()
    }

    fun moveNode(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun inRadius(x: Float, y: Float): Boolean {
        val xBool = this.x - 1 < x && x < this.x + 1

        val yBool = this.y - 1 < y && y < this.y + 1

        return xBool && yBool
    }
}