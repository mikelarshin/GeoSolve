package open.geosolve.euclid2d.element.primitive

import open.geosolve.euclid2d.algorithm.MathHelper.distance
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Line(
    val first: Point,
    val second: Point,
    var name: String = "[${first.name}${second.name}]"
) : PrimitiveElement() {

    val length: Float
        get() = distance(first, second)

    // FIXME(PERFORMANCE) Более быстрый алгоритм пересечения линии с точкой
    fun belongs(x: Float, y: Float) = distance(x, y) == 0f

    // FROM: https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line
    fun distance(x: Float, y: Float): Float {

        val x1 = first.x
        val y1 = first.y

        val x2 = second.x
        val y2 = second.y

        val numerator = abs(x * (y2 - y1) - y * (x2 - x1) + x2 * y1 - y2 * x1)
        val denominator = sqrt((y2 - y1).pow(2) + (x2 - x1).pow(2))

        return numerator / denominator
    }

    override fun toString(): String = "$name { length: $length }\n\t$first\n\t$second\n"
}