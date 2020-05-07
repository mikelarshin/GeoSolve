package open.geosolve.geosolve.model.helper

import kotlin.math.hypot

object MathHelper {

    fun distanceBetweenPoints(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) = hypot(x1 - x2, y1 - y2)
}