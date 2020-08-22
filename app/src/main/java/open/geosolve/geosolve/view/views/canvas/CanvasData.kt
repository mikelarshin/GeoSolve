package open.geosolve.geosolve.view.views.canvas

import open.geosolve.geosolve.model.canvasData
import open.geosolve.geosolve.model.data.generalized.SolveGraph

class CanvasData {
    init {
        canvasData = this
    }

    var find: SolveGraph? = null

    val figureList = FigureSet()

    val allNodes = NodeSet(figureList)
        get() {
            field.update()
            return field
        }
    val allLines = LineSet(figureList)
        get() {
            field.update()
            return field
        }
    val allAngles = AngleSet(figureList)
        get() {
            field.update()
            return field
        }
    val allCircles = CircleSet(figureList)
        get() {
            field.update()
            return field
        }
}