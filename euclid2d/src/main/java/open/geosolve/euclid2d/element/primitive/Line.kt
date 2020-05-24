package open.geosolve.euclid2d.element.primitive

import open.geosolve.euclid2d.algorithm.MathHelper.distance

data class Line(
    val first: Point,
    val second: Point,
    var name: String = "[${first.name}${second.name}]"
) : PrimitiveElement() {

    val length: Float
        get() = distance(first, second)

    private fun belongs(x: Float, y: Float) {
        TODO("Принадлежит ли точка прямой? ")
    }

    override fun toString(): String = "$name { length: $length }\n\t$first\n\t$second\n"
}