package open.geosolve.geosolve.presentation.canvas

import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.model.canvas.tools.AddTool
import open.geosolve.geosolve.model.canvas.tools.BaseTool
import open.geosolve.geosolve.model.canvas.tools.Tool

class CanvasPresenter {
    var customTool: Tool? = null

    var tool: Tool = AddTool
    var canvasScreenPresenter: CanvasScreenPresenter? = null

    fun onTouchDown(point: XYPoint) {
        tool.onTouchDown(point)
        customTool?.onTouchDown(point)
    }

    fun onTouchMove(point: XYPoint) {
        tool.onTouchMove(point)
        customTool?.onTouchMove(point)
    }

    fun onTouchUp(point: XYPoint) {
        tool.onTouchUp(point)

        if (BaseTool.movementWasNot)
            canvasScreenPresenter?.solveAndCallBack() // решать только когда было не передвижение

        customTool?.onTouchUp(point)
    }
}