package open.geosolve.geosolve.view.canvas.draw

import open.geosolve.geosolve.view.canvas.draw.DrawConstant.heightCanvas
import open.geosolve.geosolve.view.canvas.draw.DrawConstant.scale
import open.geosolve.geosolve.view.canvas.draw.DrawConstant.widthCanvas

enum class SystemCoordinate(val convertX: (Float) -> Float,
                            val convertY: (Float) -> Float,
                            val convertDistance: (Float) -> Float) {
    ABSOLUTE(
        { x -> widthCanvas / 2 - (scale * -x) },
        { y -> heightCanvas / 2 - (scale * y) },
        { distance -> distance * scale }),

    DECART(
        { x -> x },
        { y -> y },
        { distance -> distance})
}

fun xToDecart(x: Float) = (x - widthCanvas / 2) / scale
fun yToDecart(y: Float) = (heightCanvas / 2 - y) / scale