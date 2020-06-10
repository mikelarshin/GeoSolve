package open.geosolve.euclid2d.element.complex.triangle

import open.geosolve.euclid2d.element.complex.Polygon
import open.geosolve.euclid2d.element.primitive.Angle
import open.geosolve.euclid2d.element.primitive.Line
import open.geosolve.euclid2d.element.primitive.metric.Degree

class RightTriangle(polygon: Polygon) : Polygon() {

    init {
        this._angles.addAll(0, polygon.angles.toMutableList())
        this._lines.addAll(0, polygon.lines.toMutableList())
        this._points.addAll(0, polygon.points.toMutableList())

        check(rightAngle != null) { "Right triangle hasn't right angle!" }
    }

    val rightAngle: Angle?
        get() = _angles.find { angle -> angle.value == Degree(90f) }

    val legs: Pair<Line, Line>
        get() {
            return Pair(rightAngle!!.firstLine, rightAngle!!.secondLine)
        }
}