package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.math.XYPoint

object SetValueTool : BaseTool() {

    lateinit var callBack: (Int, SolveGraph) -> Unit

    override fun onTouchElement(point: XYPoint) {
        if (selectElement is SolveGraph) {
            val message = when (selectElement) {
                is Line -> R.string.alert_set_line
                is Angle -> R.string.alert_set_angle
                is Circle -> R.string.alert_set_circle
                else -> null!!
            }

            callBack(message, selectElement as SolveGraph)
        }
    }
}