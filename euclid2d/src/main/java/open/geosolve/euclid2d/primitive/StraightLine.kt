package open.geosolve.euclid2d.primitive

data class StraightLine(
    val intermediate1: Point,
    val intermediate2: Point,
    var name: String = "(${intermediate1.name}${intermediate2.name})"
) {
    override fun toString(): String = name
}