package open.geosolve.geosolve.presentation.presenter

import open.geosolve.geosolve.model.tools.AddTool
import open.geosolve.geosolve.model.tools.BaseTool
import open.geosolve.geosolve.model.tools.Tool

class CanvasPresenter {
    var updateEvent: () -> Unit = {}

    var tool: Tool = AddTool
    var canvasScreenPresenter: CanvasScreenPresenter? = null

    fun onTouchDown(touchX: Float, touchY: Float) {
        tool.onTouchDown(touchX, touchY)
        updateEvent()
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        tool.onTouchMove(touchX, touchY)
        updateEvent()
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        tool.onTouchUp(touchX, touchY)

        if (BaseTool.movementWasNot)
            canvasScreenPresenter?.solveAndCallBack() // решать только когда было не передвижение

        updateEvent()
    }
}