package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.model.tools.AddTool
import open.geosolve.geosolve.model.tools.BaseTool

fun makeTriangleOne() {
    AddTool.cycleTouch(-10f, -10f)
    AddTool.cycleTouch(10f, -10f)
    AddTool.cycleTouch(-10f, 10f)
    AddTool.cycleTouch(-10f, -10f)
}

fun makeTriangleTwo() {
    AddTool.cycleTouch(-10f, -10f)
    AddTool.cycleTouch(10f, -10f)
    AddTool.cycleTouch(10f, 10f)
    AddTool.cycleTouch(-10f, -10f)
}

fun makeCircle() {
    BaseTool.moveQuantity = 5
    AddTool.onTouchMove(0f, 0f)
    AddTool.onTouchMove(10f, 0f)
    AddTool.onTouchUp(10f, 0f)
}

fun makeRectangle() {
    BaseTool.moveQuantity = 5
    AddTool.onTouchMove(0f, 0f)
    AddTool.onTouchMove(10f, 0f)
    AddTool.onTouchUp(10f, 0f)
}