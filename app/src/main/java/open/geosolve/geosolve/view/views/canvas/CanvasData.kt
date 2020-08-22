package open.geosolve.geosolve.view.views.canvas

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.generalized.SolveGraph

class CanvasData {
    var find: SolveGraph? = null

    val figureList = object : ArrayList<Figure>() {
        init {
            this.add(Figure())
        }

        override fun clear() {
            super.clear()
            find = null
            this.add(Figure())
        }

        fun nextFigure() {
            this.add(Figure())
        }
    }

    val AllNodes = NodeSet(figureList)
        get() {
            field.update()
            return field
        }
    val AllLines = LineList(figureList)
        get() {
            field.update()
            return field
        }
    val AllAngles = AngleList(figureList)
        get() {
            field.update()
            return field
        }
    val AllCircles = CircleList(figureList)
        get() {
            field.update()
            return field
        }
}