package open.geosolve.geosolve.view.canvas.draw

import android.graphics.Canvas
import open.geosolve.geosolve.model.canvas.controllers.AllCircles
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.controllers.find
import open.geosolve.geosolve.view.helpers.formatValueString
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.CHAR_SIZE

object DrawCircles {
    fun drawCircles(canvas: Canvas) {
        for (circle in AllCircles) {
            val centerNode = circle.centerNode

            canvas.drawCircle(
                centerNode.x,
                centerNode.y,
                circle.radius,
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
                centerNode.x - PaintConstant.CHAR_MARGIN,
                centerNode.y - PaintConstant.CHAR_MARGIN,
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
            markedCircle.radius,
            PaintConstant.mPaintMarkCircle
        )
    }

    fun drawCirclesValue(canvas: Canvas) {
        for (circle in AllCircles) {
            if (circle.getValue() == null) continue

            val text = formatValueString(circle)

            val x = circle.centerNode.x - CHAR_SIZE * text.length / 3.5f
            val y = circle.centerNode.y + CHAR_SIZE / 3.5f

            canvas.drawText(text, x, y - circle.radius, PaintConstant.mPaintText)
        }
    }
}