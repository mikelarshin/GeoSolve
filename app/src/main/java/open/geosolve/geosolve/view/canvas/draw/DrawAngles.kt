package open.geosolve.geosolve.view.canvas.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import open.geosolve.geosolve.model.canvas.controllers.AllAngles
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.controllers.find
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.model.canvas.math.MathUtil.getDegree
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.CHAR_MARGIN
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.mPaintAngle
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.mPaintAngleMark
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.mPaintText
import open.geosolve.geosolve.view.helpers.formatValueString

object DrawAngles {
    fun drawAngles(canvas: Canvas) {
        for (angle in AllAngles) {
            drawAngle(canvas, angle)
        }
    }

    fun drawAnglesMark(canvas: Canvas) {
        if (find !is Angle) return
        val markedAngle = find as Angle
        drawAngle(canvas, markedAngle, mPaintAngleMark)
    }

    fun drawAnglesValue(canvas: Canvas) {
        for (angle in AllAngles) {
            if (angle.getValue() == null) continue
                // TODO() сделать отрисовку относительно угла
            canvas.drawText(
                formatValueString(angle),
                angle.angleNode.x + CHAR_MARGIN,
                angle.angleNode.y + CHAR_MARGIN,
                mPaintText
            )
        }
    }

    private fun drawAngle(canvas: Canvas, angle: Angle, mPaint: Paint = mPaintAngle) {
        val centerX = angle.angleNode.x
        val centerY = angle.angleNode.y

//        if (angle.getValue() == 90f) {
//            val rectDraw = RectF(
//                centerX,
//                centerY - ANGLE_ARC_RADIUS / 1.5f,
//                centerX + ANGLE_ARC_RADIUS / 1.5f,
//                centerY
//            )
//
//            canvas.drawRect(rectDraw, mPaint) // TODO(сделать выравнивание по углам)
//        } else {
            val rectDraw = RectF(
                centerX - ANGLE_ARC_RADIUS,
                centerY - ANGLE_ARC_RADIUS,
                centerX + ANGLE_ARC_RADIUS,
                centerY + ANGLE_ARC_RADIUS
            )

            val sweepAngle = getDegree(angle.startNode, angle.angleNode, angle.finalNode)

            val startPoint = XYPoint(
                angle.angleNode.x,
                angle.angleNode.y + 1f
            )

            val startAngle = getDegree(startPoint, angle.angleNode, angle.startNode) + 90f

            canvas.drawArc(rectDraw, startAngle, sweepAngle, false, mPaint)
//        }
    }
}