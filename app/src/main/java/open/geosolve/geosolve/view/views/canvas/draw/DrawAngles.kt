package open.geosolve.geosolve.view.views.canvas.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import open.geosolve.geosolve.AllAngles
import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.math.MathUtil.getDegree
import open.geosolve.geosolve.view.DesignUtil.formatValueString
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.CHAR_MARGIN
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintAngle
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintAngleMark
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintText

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
//            canvas.drawRect(rectDraw, mPaint) // TODO(построение такого квадратика работает только если угол слева внизу)
//        } else {
            val rectDraw = RectF(
                centerX - ANGLE_ARC_RADIUS,
                centerY - ANGLE_ARC_RADIUS,
                centerX + ANGLE_ARC_RADIUS,
                centerY + ANGLE_ARC_RADIUS
            )

            val sweepAngle = getDegree(angle.startNode, angle.angleNode, angle.finalNode)

            systemCoordinate = SystemCoordinate.DECART
            val startNode = Node(  // TODO(Replace Node with MathPoint)
                angle.angleNode.x,
                angle.angleNode.y + 1f
            )
            systemCoordinate = SystemCoordinate.ABSOLUTE

            val startAngle = getDegree(startNode, angle.angleNode, angle.startNode) - 90f

            canvas.drawArc(rectDraw, startAngle, sweepAngle, false, mPaint)
//        }
    }
}