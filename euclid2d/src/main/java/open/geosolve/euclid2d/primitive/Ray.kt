package open.geosolve.euclid2d.primitive

data class Ray(
    val source: Point,
    val intermediate: Point,
    var name: String = "[${source.name}${intermediate.name})"
) {
    override fun toString(): String = name
}