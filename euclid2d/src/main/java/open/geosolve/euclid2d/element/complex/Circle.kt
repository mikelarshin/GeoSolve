package open.geosolve.euclid2d.element.complex

import open.geosolve.euclid2d.element.primitive.Point
import kotlin.math.pow


class Circle(
    x: Float,
    y: Float,
    var radius: Float
) : ComplexElement() {

    val center = Point(x, y)

    override fun isDescribedAround(element: ComplexElement): Boolean {
        return when (element) {
            is Polygon -> {
                return element.points.find { !belongs(it.x, it.y) } == null
            }

            else -> false
        }
    }

    private fun belongs(x: Float, y: Float): Boolean =
        ((x - center.x).pow(2) + (y - center.y).pow(2)) - (radius * radius) < 0.001

    override fun isInscribedInto(element: ComplexElement): Boolean {
        return when (element) {
            is Polygon -> {
                element.lines.find { it.distance(center.x, center.y) != radius } == null
            }

            else -> false
        }
    }

    override fun toString(): String = "\uD83D\uDF8A ${center.name} { radius: $radius }"

    override fun equals(other: Any?): Boolean {
        return if (other !is Circle) false
        else other.center == center && other.radius == radius
    }

    override fun hashCode(): Int {
        return 31 * radius.hashCode() + center.hashCode()
    }
}