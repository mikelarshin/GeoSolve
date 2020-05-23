package open.geosolve.euclid2d.element.primitive

data class Circle(
    val center: Point,
    var radius: Float
) : PrimitiveElement() {
    override fun toString(): String = "\uD83D\uDF8A ${center.name} { radius: $radius }"
}