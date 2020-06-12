package open.geosolve.geosolve

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object GlobalFiguresController {
    var find: SolveGraph? = null

    object FigureList : ArrayList<Figure>() {
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
}