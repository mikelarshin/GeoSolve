package open.geosolve.geosolve.model.canvas.data.generalized

import open.geosolve.geosolve.model.canvas.math.XYPoint

interface Element { // any
    fun inRadius(point: XYPoint): Boolean
    fun remove() // TODO(rewrite remove system)
}