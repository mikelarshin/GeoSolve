package open.geosolve.euclid2d.primitive

import open.geosolve.euclid2d.primitive.metric.AngleValue
import open.geosolve.euclid2d.primitive.metric.Degree

data class Angle(
    val first: Point,
    val center: Point,
    val second: Point,
    var value: AngleValue = Degree(),
    var name: String = "∠${first.name}${center.name}${second.name} { $value }"
)