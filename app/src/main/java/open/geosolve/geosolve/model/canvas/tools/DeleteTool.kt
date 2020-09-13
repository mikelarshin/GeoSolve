package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.controllers.find
import open.geosolve.geosolve.model.canvas.math.XYPoint

object DeleteTool : BaseTool() {

    override fun onTouchElement(point: XYPoint) {
        selectElement?.remove()

        if (find == selectElement)
            find = null
        if (AddTool.lastNode == selectElement)
            AddTool.lastNode = null
    }
}