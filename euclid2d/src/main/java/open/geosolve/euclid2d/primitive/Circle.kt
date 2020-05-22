package open.geosolve.euclid2d.primitive

data class Circle(
    val center: Point,
    var radius: Float
) {
    override fun toString(): String = "\uD83D\uDF8A ${center.name} { radius: $radius }"
}