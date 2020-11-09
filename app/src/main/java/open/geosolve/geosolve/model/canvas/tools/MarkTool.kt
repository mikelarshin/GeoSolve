package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.find
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.XYPoint

object MarkTool : BaseTool() {
    override fun onTouchElement(point: XYPoint) {
        if (selectElement is SolveGraph)
            find = selectElement as SolveGraph
    }
}