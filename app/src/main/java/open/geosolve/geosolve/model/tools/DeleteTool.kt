package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.model.find
import open.geosolve.geosolve.model.tools.AddTool.lastNode

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