package open.geosolve.euclid2d.element.primitive

data class Point(
    var x: Float,
    var y: Float,
    var z: Float,
    var name: Char
) : PrimitiveElement() {
    override fun toString(): String =
        "•$name { x: $x, y: $y, z: $z }"
}