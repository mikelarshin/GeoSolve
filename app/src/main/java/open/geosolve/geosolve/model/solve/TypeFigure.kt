package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.canvas.data.containers.Figure

abstract class TypeFigure(parentType: TypeFigure?) { // null only in AnyFigure
    abstract fun isMatch(figure: Figure): Boolean
    open fun setGraphs(figure: Figure) {}
    val childTypes: MutableSet<TypeFigure> = mutableSetOf()

    init {
        parentType?.childTypes?.add(this)
    }

//    fun collectData(figure: Figure): Boolean // TODO(implement)
//    fun isCorrectData(figure: Figure): Boolean // TODO(implement)
}
