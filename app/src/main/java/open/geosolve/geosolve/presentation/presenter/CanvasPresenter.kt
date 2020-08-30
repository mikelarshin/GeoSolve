package open.geosolve.geosolve.presentation.presenter

import open.geosolve.geosolve.model.canvas.tools.AddTool
import open.geosolve.geosolve.model.canvas.tools.BaseTool
import open.geosolve.geosolve.model.canvas.tools.Tool

class CanvasPresenter {
    var customTool: Tool? = null

    var tool: Tool = AddTool
    var canvasScreenPresenter: CanvasScreenPresenter? = null

    fun onTouchDown(touchX: Float, touchY: Float) {
        tool.onTouchDown(touchX, touchY)
        customTool?.onTouchDown(touchX, touchY)
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        tool.onTouchMove(touchX, touchY)
        customTool?.onTouchMove(touchX, touchY)
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        tool.onTouchUp(touchX, touchY)

        if (BaseTool.movementWasNot)
            canvasScreenPresenter?.solveAndCallBack() // решать только когда было не передвижение

        customTool?.onTouchUp(touchX, touchY)
    }
}