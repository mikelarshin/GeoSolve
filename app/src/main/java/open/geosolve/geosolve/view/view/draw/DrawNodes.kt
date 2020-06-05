package open.geosolve.geosolve.view.view.draw

import android.graphics.Canvas
import open.geosolve.geosolve.GlobalFiguresController.allNodes
import open.geosolve.geosolve.view.view.draw.PaintConstant.POINT_SIZE
import open.geosolve.geosolve.view.view.draw.PaintConstant.TEXT_MARGIN
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintNode
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintText

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
                node.char,
                node.x - TEXT_MARGIN,
                node.y - TEXT_MARGIN,
                mPaintText
            )
        }
    }
}