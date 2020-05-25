package open.geosolve.euclid2d.element.complex

import open.geosolve.euclid2d.element.primitive.Angle
import open.geosolve.euclid2d.element.primitive.Line
import open.geosolve.euclid2d.element.primitive.Point

@Suppress("MemberVisibilityCanBePrivate")
class Polygon : ComplexElement() {

    protected val _points = mutableListOf<Point>()
    val points: List<Point> get() = _points

    protected val _lines = mutableListOf<Line>()
    val lines: List<Line> get() = _lines

    protected val _angles = mutableListOf<Angle>()
    val angles: List<Angle> get() = _angles

    private val letters = ('a'..'z').iterator()

    val isPolygonClosed: Boolean
        get() {
            return if (_lines.size <= 1 || _points.size <= 1) {
                false
            } else {

                val lastLine = _lines.last()
                val firstPoint = _points.first()
                val lastPoint = _points.last()

                (lastLine.first == lastPoint && lastLine.second == firstPoint)
            }
        }

    override fun isDescribedAround(element: ComplexElement): Boolean {
        return when (element) {
            is Polygon -> {
                element.points.forEach { p ->
                    if (lines.find { !it.belongs(p.x, p.y) } != null) return false
                }

                true
            }

            else -> element.isInscribedInto(this)
        }
    }

    override fun isInscribedInto(element: ComplexElement): Boolean {
        return when (element) {
            is Polygon -> {
                points.forEach { p ->
                    if (element.lines.find { !it.belongs(p.x, p.y) } != null) return false
                }

                true
            }

            else -> element.isDescribedAround(this)
        }
    }

    fun appendPoint(x: Float, y: Float) {

        check(!isPolygonClosed) { "Polygon already closed" }

        // FIXME(BUG) Если в полигоне больше 26 точек, итератор может вернуть ошибку
        _points += Point(x, y, name = letters.next())

        // Линия между последней точкой и предыдыщей
        if (_points.size > 1) {
            _lines += Line(_points[_points.lastIndex - 1], _points[_points.lastIndex])
        }
    }

    fun close() {

        // Замыкаем фигуру, соединяя первую и последнюю точку
        _lines += Line(_points.last(), _points.first())
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is Polygon)
            false
        else
            other.points == points
    }

    override fun toString(): String {
        var str = "Полигон: \n"

        lines.forEach { str += it }

        return str
    }
}