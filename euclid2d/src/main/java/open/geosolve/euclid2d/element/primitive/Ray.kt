package open.geosolve.euclid2d.element.primitive

data class Ray(
    val source: Point,
    val intermediate: Point,
    var name: String = "[${source.name}${intermediate.name})"
) : PrimitiveElement() {
    override fun toString(): String = name
}