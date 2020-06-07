package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object SetValueTool : BaseTool() {

    lateinit var callBack: (Int, SolveGraph) -> Unit

    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot && selectElement is SolveGraph) {
            val message = when (selectElement) {
                is Line -> R.string.alert_set_line
                is Angle -> R.string.alert_set_angle
                is Circle -> R.string.alert_set_circle
                else -> null!!
            }

            callBack(message, selectElement as SolveGraph)
        }

        super.onTouchUp(x, y)
    }
}