package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.controllers.find
import open.geosolve.geosolve.model.canvas.math.XYPoint

object MarkTool : BaseTool() {
    override fun onTouchElement(point: XYPoint) {
        if (selectElement is SolveGraph)
            find = selectElement as SolveGraph
    }
}