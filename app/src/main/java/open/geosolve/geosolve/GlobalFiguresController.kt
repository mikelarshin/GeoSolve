package open.geosolve.geosolve

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import java.util.*

object GlobalFiguresController {
    var figureList: MutableList<Figure> = mutableListOf(Figure())
    var find: SolveGraph? = null
}