package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeCanvasData
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.find
import open.geosolve.geosolve.model.canvas.math.XYPoint

object DeleteTool : BaseTool() {

    override fun onTouchElement(point: XYPoint) {
        selectElement?.remove()

        if (find == selectElement)
            find = null
        if (activeCanvasData.lastNode == selectElement)
            activeCanvasData.lastNode = null
    }
}