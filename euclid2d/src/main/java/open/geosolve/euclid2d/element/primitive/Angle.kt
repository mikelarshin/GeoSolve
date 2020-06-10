package open.geosolve.euclid2d.element.primitive

import open.geosolve.euclid2d.element.primitive.metric.AngleValue
import open.geosolve.euclid2d.element.primitive.metric.Degree

// TODO(CODE) Optional calculate value by points
class Angle(first: Line, second: Line, value: AngleValue? = null) : PrimitiveElement() {

    private var pseudoValue: AngleValue? = value
    var value: AngleValue
        get() = pseudoValue ?: Degree()  // FIXME(CODE) Calculate value by points
        set(value) {
            pseudoValue = value
        }

    val firstLine: Line = first
    val secondLine: Line = second

    val firstPoint: Point get() = firstLine.first
    val centerPoint: Point get() = secondLine.first
    val secondPoint: Point get() = secondLine.second

    /*constructor(first: Point, center: Point, second: Point, value: AngleValue? = null) : super() {
        this.firstLine = Line(first, center)
        this.secondLine = Line(second, center)
        this.pseudoValue = value
    }*/

    override fun toString(): String {
        return "∠${firstPoint.name}${centerPoint.name}${secondPoint.name} { $value }"
    }
}