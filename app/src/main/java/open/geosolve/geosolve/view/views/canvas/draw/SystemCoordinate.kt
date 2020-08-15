package open.geosolve.geosolve.view.views.canvas.draw

import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.heightCanvas
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.scale
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.widthCanvas

enum class SystemCoordinate(val convertX: (Float) -> Float,
                            val convertY: (Float) -> Float) {
    ABSOLUTE({ x -> widthCanvas / 2 - (scale * -x) },
        { y -> heightCanvas / 2 - (scale * y) }),

    DECART({ x -> x },
        { y -> y })
}

fun xToDecart(x: Float) = (x - widthCanvas / 2) / scale
fun yToDecart(y: Float) = (heightCanvas / 2 - y) / scale