package open.geosolve.euclid2d.element.complex

import open.geosolve.euclid2d.element.primitive.Angle
import open.geosolve.euclid2d.element.primitive.Line
import open.geosolve.euclid2d.element.primitive.Point

class Polygon : ComplexElement() {

    val points = mutableListOf<Point>()
    val lines = mutableListOf<Line>()
    val angles = mutableListOf<Angle>()
}