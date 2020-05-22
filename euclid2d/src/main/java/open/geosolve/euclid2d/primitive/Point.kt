package open.geosolve.euclid2d.primitive

data class Point(
    var x: Float,
    var y: Float,
    var z: Float,
    var name: Char
) {
    override fun toString(): String =
        "•$name { x: $x, y: $y, z: $z }"
}