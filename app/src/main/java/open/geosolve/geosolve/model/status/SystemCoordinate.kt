package open.geosolve.geosolve.model.status

import open.geosolve.geosolve.App.Companion.heightCanvas
import open.geosolve.geosolve.App.Companion.scaleForX
import open.geosolve.geosolve.App.Companion.scaleForY
import open.geosolve.geosolve.App.Companion.widthCanvas

enum class SystemCoordinate(val transformationMethodX: (Float) -> Float,
                            val transformationMethodY: (Float) -> Float) {
    ABSOLUTE({ value -> widthCanvas / 2 - (scaleForX * -value) },
        { value -> heightCanvas / 2 - (scaleForY * value) }),

    DECART({ value -> value },
        { value -> value })
}