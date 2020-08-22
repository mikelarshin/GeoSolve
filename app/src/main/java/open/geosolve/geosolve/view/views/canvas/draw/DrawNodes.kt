package open.geosolve.geosolve.view.views.canvas.draw

import android.graphics.Canvas
import open.geosolve.geosolve.model.AllNodes
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.CHAR_MARGIN
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.POINT_SIZE
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintNode
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintText

object DrawNodes {
    fun drawNodes(canvas: Canvas) {
        for (node in AllNodes)
            canvas.drawCircle(node.x, node.y,
                POINT_SIZE,
                mPaintNode
            )
    }

    fun drawNodesName(canvas: Canvas) {
        for (node in AllNodes) {
            canvas.drawText(
                node.toString(),
                node.x - CHAR_MARGIN,
                node.y - CHAR_MARGIN,
                mPaintText
            )
        }
    }
}