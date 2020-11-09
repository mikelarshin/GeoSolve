package open.geosolve.geosolve.view.canvas

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData
import open.geosolve.geosolve.view.canvas.draw.DrawAngles.drawAngles
import open.geosolve.geosolve.view.canvas.draw.DrawAngles.drawAnglesMark
import open.geosolve.geosolve.view.canvas.draw.DrawAngles.drawAnglesValue
import open.geosolve.geosolve.view.canvas.draw.DrawCircles.drawCircles
import open.geosolve.geosolve.view.canvas.draw.DrawCircles.drawCirclesMark
import open.geosolve.geosolve.view.canvas.draw.DrawCircles.drawCirclesValue
import open.geosolve.geosolve.view.canvas.draw.DrawConstant
import open.geosolve.geosolve.view.canvas.draw.DrawConstant.systemCoordinate
import open.geosolve.geosolve.view.canvas.draw.DrawLines.drawLines
import open.geosolve.geosolve.view.canvas.draw.DrawLines.drawLinesMark
import open.geosolve.geosolve.view.canvas.draw.DrawLines.drawLinesValue
import open.geosolve.geosolve.view.canvas.draw.DrawNodes.drawNodes
import open.geosolve.geosolve.view.canvas.draw.DrawNodes.drawNodesName
import open.geosolve.geosolve.view.canvas.draw.PaintConstant
import open.geosolve.geosolve.view.canvas.draw.PaintConstant.POINT_SIZE
import open.geosolve.geosolve.view.canvas.draw.SystemCoordinate

abstract class DrawCanvasView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var dataCanvas = CanvasData()

    fun updateScale() {
        DrawConstant.heightCanvas = height
        DrawConstant.widthCanvas = width

        DrawConstant.scale = ((height + width) / 2) / POINT_SIZE // 1 coordinate unit equals 1 Node

        PaintConstant.canvasContext = context

        dataCanvas.makeActive()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        systemCoordinate = SystemCoordinate.ABSOLUTE
        updateScale()

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
