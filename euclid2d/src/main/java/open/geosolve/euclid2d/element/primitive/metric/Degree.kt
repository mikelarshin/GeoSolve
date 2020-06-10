package open.geosolve.euclid2d.element.primitive.metric

import open.geosolve.euclid2d.helper.LimitFloat

class Degree(
    degree: Float = 0f,
    minute: Float = 0f,
    second: Float = 0f
) : AngleValue {

    val degree = LimitFloat(degree, 0f, 360f)
    val minute = LimitFloat(minute, 0f, 60f)
    val second = LimitFloat(second, 0f, 60f)

    // TODO(CODE) Compare with other angle values
    override fun equals(other: Any?): Boolean {
        if (other !is Degree)
            return false
        else
            return degree == other.degree &&
                    minute == other.minute &&
                    second == other.second
    }

    override fun toString(): String =
        "$degree°$minute′$second″"
}

