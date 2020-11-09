package open.geosolve.geosolve.view.canvas.draw

import android.graphics.Canvas
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.allNodes
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.CHAR_MARGIN
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.POINT_SIZE
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.mPaintNode
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.mPaintText

object DrawNodes {
    fun drawNodes(canvas: Canvas) {
        for (node in allNodes)
            canvas.drawCircle(node.x, node.y,
                POINT_SIZE,
                mPaintNode
            )
    }

    fun drawNodesName(canvas: Canvas) {
        for (node in allNodes) {
            canvas.drawText(
                node.toString(),
                node.x - CHAR_MARGIN,
                node.y - CHAR_MARGIN,
                mPaintText
            )
        }
    }
}