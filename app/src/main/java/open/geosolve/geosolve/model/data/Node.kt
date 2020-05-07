package open.geosolve.geosolve.model.data

import kotlin.properties.Delegates

class Node(var x: Float, var y: Float) {

    private val POINT_SIZE = 20f

    var char by Delegates.notNull<Char>()
    override fun toString(): String = char.toString()

    var startLine: Line? = null
    var finalLine: Line? = null

    var startAngle: Angle? = null
    var centerAngle: Angle? = null
    var finalAngle: Angle? = null

    fun deleteConnections() {
        startLine?.delConnection()
        finalLine?.delConnection()

        startAngle?.deleteConnections()
        centerAngle?.deleteConnections()
        finalAngle?.deleteConnections()
    }

    fun moveNode(x: Float, y: Float) {
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