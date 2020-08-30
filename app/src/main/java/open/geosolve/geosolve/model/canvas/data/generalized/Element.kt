package open.geosolve.geosolve.model.canvas.data.generalized

interface Element { // any
    fun inRadius(x: Float, y: Float): Boolean
    fun remove() // TODO(rewrite remove system)
}