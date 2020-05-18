package open.geosolve.geosolve.view.view.draw

import android.graphics.Canvas
import open.geosolve.geosolve.App
import open.geosolve.geosolve.model.data.Circle

object DrawCircles {
    fun drawCircles(canvas: Canvas) {
        for (circle in App.allCircles) {
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
                centerNode.char.toString(),
                centerNode.x - PaintConstant.TEXT_MARGIN,
                centerNode.y - PaintConstant.TEXT_MARGIN,
                PaintConstant.mPaintText
            )
        }
    }

    fun drawCirclesMark(canvas: Canvas) {
        if (App.find !is Circle) return

        val markedCircle = App.find as Circle

        canvas.drawCircle(
            markedCircle.centerNode.x,
            markedCircle.centerNode.y,
            markedCircle.drawRadius,
            PaintConstant.mPaintMarkCircle
        )
    }
}