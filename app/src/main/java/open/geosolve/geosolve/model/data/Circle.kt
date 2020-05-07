package open.geosolve.geosolve.model.data

import kotlin.math.hypot

class Circle(val centerNode: Node, val radiusNode: Node) : Element(){

    // all logic solve in abstract Element

    val drawRadius: Float
        get() = hypot(centerNode.x - radiusNode.x, centerNode.y - radiusNode.y)

    fun moveDisplayRadius(x: Float, y: Float){
        radiusNode.moveNode(x, y)
    }

    override fun delConnection() {
        TODO("Not yet implemented")
    }

//    fun inRadiusLine(x: Float, y: Float): Boolean {
//    }

//    fun inRadiusPoint(x: Float, y: Float): Boolean {
//        val radius = DrawCanvasView.POINT_SIZE / 20
//        val xBool = this.x - radius < x && x < this.x + radius
//
//        val yBool = this.y - radius < y && y < this.y + radius
//
//        return xBool && yBool
//    }
}