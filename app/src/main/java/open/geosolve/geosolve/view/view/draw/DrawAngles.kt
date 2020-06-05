package open.geosolve.geosolve.view.view.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import open.geosolve.geosolve.GlobalFiguresController.allAngles
import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.model.MathUtil.getAngle
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.screens.DesignUtil.formatValueString
import open.geosolve.geosolve.view.view.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.view.draw.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.view.draw.PaintConstant.TEXT_MARGIN
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintAngle
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintAngleMark
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintText

object DrawAngles {
    fun drawAngles(canvas: Canvas) {
        for (angle in allAngles) {
            drawAngle(canvas, angle)
        }
    }

    fun drawAnglesMark(canvas: Canvas) {
        if (find !is Angle) return
        val markedAngle = find as Angle
        drawAngle(canvas, markedAngle, mPaintAngleMark)
    }

    fun drawAnglesValue(canvas: Canvas) {
        for (angle in allAngles) {
            if (angle.getValue() == null) continue
                // TODO() сделать отрисовку относительно угла
            canvas.drawText(
                formatValueString(angle),
                angle.angleNode.x + TEXT_MARGIN,
                angle.angleNode.y + TEXT_MARGIN,
                mPaintText
            )
        }
    }

    private fun drawAngle(canvas: Canvas, angle: Angle, mPaint: Paint = mPaintAngle) {
        val centerX = angle.angleNode.x
        val centerY = angle.angleNode.y

        // Данный код деактивирован до появления блокировки перемещения угла
        if (angle.getValue() == 90f && false) {
            val rectDraw = RectF(
                centerX,
                centerY - ANGLE_ARC_RADIUS,
                centerX + ANGLE_ARC_RADIUS,
                centerY
            )

            canvas.drawRect(rectDraw, mPaint)
        } else {
            val rectDraw = RectF(
                centerX - ANGLE_ARC_RADIUS,
                centerY - ANGLE_ARC_RADIUS,
                centerX + ANGLE_ARC_RADIUS,
                centerY + ANGLE_ARC_RADIUS
            )

            val sweepAngle = getAngle(angle.startNode, angle.angleNode, angle.finalNode)

            systemCoordinate = SystemCoordinate.DECART
            val startNode = Node(  // TODO(Replace Node with MathPoint)
                angle.angleNode.x,
                angle.angleNode.y + 1f
            )
            systemCoordinate = SystemCoordinate.ABSOLUTE

            val startAngle = getAngle(startNode, angle.angleNode, angle.startNode) - 90f

            canvas.drawArc(rectDraw, startAngle, sweepAngle, false, mPaint)
        }
    }
}