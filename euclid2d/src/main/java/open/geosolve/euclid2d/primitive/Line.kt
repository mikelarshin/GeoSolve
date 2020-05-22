package open.geosolve.euclid2d.primitive

import open.geosolve.euclid2d.algorithm.MathHelper.distance

data class Line(
    val first: Point,
    val second: Point,
    var name: String = "[${first.name}${second.name}]"
) {

    val length: Float
        get() = distance(first, second)

    override fun toString(): String = "$name { length: $length }\n\t$first\n\t$second"
}