package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.find

object MarkTool : BaseTool() {
    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot && selectElement is SolveGraph)
            find = selectElement as SolveGraph

        super.onTouchUp(x, y)
    }
}