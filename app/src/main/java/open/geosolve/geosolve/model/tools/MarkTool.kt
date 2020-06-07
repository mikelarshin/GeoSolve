package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object MarkTool : BaseTool() {
    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot && selectElement is SolveGraph)
            find = selectElement as SolveGraph

        super.onTouchUp(x, y)
    }
}