package open.geosolve.geosolve.view.views.canvas

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import open.geosolve.geosolve.view.views.canvas.draw.SystemCoordinate
import open.geosolve.geosolve.view.views.canvas.draw.DrawAngles.drawAngles
import open.geosolve.geosolve.view.views.canvas.draw.DrawAngles.drawAnglesMark
import open.geosolve.geosolve.view.views.canvas.draw.DrawAngles.drawAnglesValue
import open.geosolve.geosolve.view.views.canvas.draw.DrawCircles.drawCircles
import open.geosolve.geosolve.view.views.canvas.draw.DrawCircles.drawCirclesMark
import open.geosolve.geosolve.view.views.canvas.draw.DrawCircles.drawCirclesValue
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant
import open.geosolve.geosolve.view.views.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.views.canvas.draw.DrawLines.drawLines
import open.geosolve.geosolve.view.views.canvas.draw.DrawLines.drawLinesMark
import open.geosolve.geosolve.view.views.canvas.draw.DrawLines.drawLinesValue
import open.geosolve.geosolve.view.views.canvas.draw.DrawNodes.drawNodes
import open.geosolve.geosolve.view.views.canvas.draw.DrawNodes.drawNodesName
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.POINT_SIZE

open class DrawCanvasView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        DrawConstant.heightCanvas = height
        DrawConstant.widthCanvas = width

        DrawConstant.scale = ((height + width) / 2) / POINT_SIZE // 1 coordinate unit equals 1 Node

        PaintConstant.canvasContext = context
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        systemCoordinate = SystemCoordinate.ABSOLUTE

        // Elements
        drawAngles(canvas)
        drawLines(canvas)
        drawCircles(canvas)

        // Mark Elements
        drawAnglesMark(canvas)
        drawLinesMark(canvas)
        drawCirclesMark(canvas)

        // Nodes
        drawNodes(canvas)

        // Text
        drawAnglesValue(canvas)
        drawLinesValue(canvas)
        drawCirclesValue(canvas)
        drawNodesName(canvas)

        systemCoordinate = SystemCoordinate.DECART
    }
}
