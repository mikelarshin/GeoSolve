package open.geosolve.euclid2d.algorithm

import open.geosolve.euclid2d.primitive.Point
import kotlin.math.pow
import kotlin.math.sqrt

object MathHelper {

    fun distance(point1: Point, point2: Point): Float =
        sqrt((point2.x - point1.x).pow(2) + (point2.y - point1.y).pow(2))
}