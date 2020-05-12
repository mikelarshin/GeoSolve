package open.geosolve.geosolve.model.status

import open.geosolve.geosolve.App.Companion.heightCanvas
import open.geosolve.geosolve.App.Companion.scale
import open.geosolve.geosolve.App.Companion.widthCanvas

enum class SystemCoordinate(val convertX: (Float) -> Float,
                            val convertY: (Float) -> Float) {
    ABSOLUTE({ x -> widthCanvas / 2 - (scale * -x) },
        { y -> heightCanvas / 2 - (scale * y) }),

    DECART({ x -> x },
        { y -> y })
}