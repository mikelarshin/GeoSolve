package open.geosolve.geosolve.view.helpers

import open.geosolve.geosolve.model.canvas.controllers.AllAngles
import open.geosolve.geosolve.model.canvas.math.MathUtil
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.model.canvas.tools.AddTool
import open.geosolve.geosolve.model.canvas.tools.BaseTool
import kotlin.math.absoluteValue

fun makeTriangleOne() {
    AddTool.cycleTouch(XYPoint(-10f, -10f))
    AddTool.cycleTouch(XYPoint(10f, -10f))
    AddTool.cycleTouch(XYPoint(-10f, 10f))
    AddTool.cycleTouch(XYPoint(-10f, -10f))
}

fun makeTriangleTwo() {
    AddTool.cycleTouch(XYPoint(-10f, -10f))
    AddTool.cycleTouch(XYPoint(10f, -10f))
    AddTool.cycleTouch(XYPoint(10f, 10f))
    AddTool.cycleTouch(XYPoint(-10f, -10f))
}

fun makeRealAngles() {
    AllAngles.forEach { angle ->
        angle.setDependentValueDraw { MathUtil.getDegree(angle).absoluteValue }
    }
}

fun makeCircle() {
    BaseTool.moveQuantity = 5
    AddTool.onTouchMove(XYPoint(0f, 0f))
    AddTool.onTouchMove(XYPoint(10f, 0f))
    AddTool.onTouchUp(XYPoint(10f, 0f))
}

fun makeRectangle() {
    BaseTool.moveQuantity = 5
    AddTool.onTouchMove(XYPoint(0f, 0f))
    AddTool.onTouchMove(XYPoint(10f, 0f))
    AddTool.onTouchUp(XYPoint(10f, 0f))
}