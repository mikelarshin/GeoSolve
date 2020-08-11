package open.geosolve.geosolve.view.views.canvas.draw

import android.graphics.Canvas
import open.geosolve.geosolve.AllCircles
import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.view.DesignUtil
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.TEXT_SIZE

object DrawCircles {
    fun drawCircles(canvas: Canvas) {
        for (circle in AllCircles) {
            val centerNode = circle.centerNode

            canvas.drawCircle(
                centerNode.x,
                centerNode.y,
                circle.drawRadius,
                PaintConstant.mPaintCircle
            )

            canvas.drawCircle(
                centerNode.x,
                centerNode.y,
                PaintConstant.POINT_SIZE,
                PaintConstant.mPaintNode
            )

            canvas.drawText(
                centerNode.toString(),
                centerNode.x - PaintConstant.TEXT_MARGIN,
                centerNode.y - PaintConstant.TEXT_MARGIN,
                PaintConstant.mPaintText
            )
        }
    }

    fun drawCirclesMark(canvas: Canvas) {
        if (find !is Circle) return

        val markedCircle = find as Circle

        canvas.drawCircle(
            markedCircle.centerNode.x,
            markedCircle.centerNode.y,
            markedCircle.drawRadius,
            PaintConstant.mPaintMarkCircle
        )
    }

    fun drawCirclesValue(canvas: Canvas) {
        for (circle in AllCircles) {
            if (circle.getValue() == null) continue

            val text = DesignUtil.formatValueString(circle)

            val x = circle.centerNode.x - TEXT_SIZE * text.length / 3.5f
            val y = circle.centerNode.y + TEXT_SIZE / 3.5f

            canvas.drawText(text, x, y - circle.drawRadius, PaintConstant.mPaintText)
        }
    }
}