package open.geosolve.euclid2d.primitive.metric

import open.geosolve.euclid2d.helper.LimitFloat

class Degree(
    degree: Float = 0f,
    minute: Float = 0f,
    second: Float = 0f
) : AngleValue {

    val degree = LimitFloat(degree, 0f, 360f)
    val minute = LimitFloat(minute, 0f, 60f)
    val second = LimitFloat(second, 0f, 60f)

    override fun toString(): String =
        "$degree°$minute′$second″"
}

