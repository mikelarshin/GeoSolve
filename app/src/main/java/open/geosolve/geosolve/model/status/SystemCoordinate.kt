package open.geosolve.geosolve.model.status

import open.geosolve.geosolve.view.view.draw.DrawConstant.heightCanvas
import open.geosolve.geosolve.view.view.draw.DrawConstant.scale
import open.geosolve.geosolve.view.view.draw.DrawConstant.widthCanvas

enum class SystemCoordinate(val convertX: (Float) -> Float,
                            val convertY: (Float) -> Float) {
    ABSOLUTE({ x -> widthCanvas / 2 - (scale * -x) },
        { y -> heightCanvas / 2 - (scale * y) }),

    DECART({ x -> x },
        { y -> y })
}