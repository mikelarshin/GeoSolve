package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.find

object MarkTool : BaseTool() {
    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot && selectElement is SolveGraph)
            find = selectElement as SolveGraph

        super.onTouchUp(x, y)
    }
}