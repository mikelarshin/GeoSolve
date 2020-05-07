//package open.geosolve.geosolve.model.data
//
//import open.geosolve.geosolve.App
//import open.geosolve.geosolve.view.view.DrawCanvasView
//import kotlin.properties.Delegates
//
//class Circle(foundX: Float, foundY: Float, val radius: Float) : Element(){
//
//    // all logic solve in abstract Element
//
//    var x: Float = foundX
//        get() = App.systemCoordinate.transformationMethodX(field)
//    var y: Float = foundY
//        get() = App.systemCoordinate.transformationMethodY(field)
//
//    var char by Delegates.notNull<Char>()
//    override fun toString(): String = char.toString()
//
//
//    fun inRadiusLine(x: Float, y: Float): Boolean {
//    }
//
//    fun inRadiusPoint(x: Float, y: Float): Boolean {
//        val radius = DrawCanvasView.POINT_SIZE / 20
//        val xBool = this.x - radius < x && x < this.x + radius
//
//        val yBool = this.y - radius < y && y < this.y + radius
//
//        return xBool && yBool
//    }
//}