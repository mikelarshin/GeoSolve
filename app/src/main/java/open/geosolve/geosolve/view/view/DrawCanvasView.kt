package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import open.geosolve.geosolve.App.Companion.systemCoordinate
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.draw.DrawAngles.drawAngles
import open.geosolve.geosolve.view.view.draw.DrawAngles.drawAnglesMark
import open.geosolve.geosolve.view.view.draw.DrawAngles.drawAnglesValue
import open.geosolve.geosolve.view.view.draw.DrawCircles.drawCircles
import open.geosolve.geosolve.view.view.draw.DrawCircles.drawCirclesMark
import open.geosolve.geosolve.view.view.draw.DrawLines.drawLines
import open.geosolve.geosolve.view.view.draw.DrawLines.drawLinesMark
import open.geosolve.geosolve.view.view.draw.DrawLines.drawLinesValue
import open.geosolve.geosolve.view.view.draw.DrawNodes.drawNodes
import open.geosolve.geosolve.view.view.draw.DrawNodes.drawNodesName
import open.geosolve.geosolve.view.view.draw.PaintConstant


open class DrawCanvasView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        PaintConstant.context = context
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
        drawNodesName(canvas)

        systemCoordinate = SystemCoordinate.DECART
    }
}
