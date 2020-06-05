package open.geosolve.geosolve.model.data.generalized

interface Element { // any
    fun inRadius(x: Float, y: Float): Boolean
    fun remove()
}