package open.geosolve.geosolve.model.solve

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import open.geosolve.geosolve.model.canvas.data.containers.Figure

abstract class TypeFigure(parentType: TypeFigure?) { // null only in AnyFigure
    abstract fun isMatch(figure: Figure): Boolean
    open fun setGraphs(figure: Figure) {}
    val childTypes: MutableSet<TypeFigure> = mutableSetOf()

    init {
        parentType?.let {
            GlobalScope.launch {
                delay(10)
                parentType.childTypes.add(this@TypeFigure)
            }
        }
    }

//    fun collectData(figure: Figure): Boolean // TODO(implement)
//    fun isCorrectData(figure: Figure): Boolean // TODO(implement)
}
