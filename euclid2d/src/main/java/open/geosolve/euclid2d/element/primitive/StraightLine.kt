package open.geosolve.euclid2d.element.primitive

data class StraightLine(
    val intermediate1: Point,
    val intermediate2: Point,
    var name: String = "(${intermediate1.name}${intermediate2.name})"
) : PrimitiveElement() {
    override fun toString(): String = name
}