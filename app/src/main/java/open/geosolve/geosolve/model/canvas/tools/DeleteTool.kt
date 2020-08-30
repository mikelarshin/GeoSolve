package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.find
import open.geosolve.geosolve.model.canvas.tools.AddTool.lastNode

object DeleteTool : BaseTool() {
    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot) {
            selectElement?.remove()

            if (find == selectElement)
                find = null
            if (lastNode == selectElement)
                lastNode = null
        }

        super.onTouchUp(x, y)
    }
}