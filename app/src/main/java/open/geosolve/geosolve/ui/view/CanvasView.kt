package open.geosolve.geosolve.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.model.Line
import open.geosolve.geosolve.repository.model.Node

// TODO Remove hardcoded values, colors and string
class CanvasView : View {

    companion object {
        private const val POINT_RADIUS: Float = 20f
    }

    private val mPaintNode = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.BLACK
    }
    private val mPaintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.parseColor("#DF1616")
    }
    private val mPaintNodeMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 5f
        Color.parseColor("#DF1616")
    }
    private val mPaintLineMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 5f
        color = Color.parseColor("#d0d0d0")
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    // TODO by InitOnce()
    private lateinit var attachedFigure: Figure

    var onTouchDown: ((x: Float, y: Float) -> Unit)? = null
    var onTouchMove: ((x: Float, y: Float) -> Unit)? = null
    var onTouchUp: ((x: Float, y: Float) -> Unit)? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (line in attachedFigure.mLines) {
            canvas.drawLine(
                line.startNode.x, line.startNode.y,
                line.finalNode.x, line.finalNode.y,
                mPaintLine
            )
        }

        if (attachedFigure.find is Line) {
            canvas.drawLine(
                (attachedFigure.find as Line).startNode.x,
                (attachedFigure.find as Line).startNode.y,
                (attachedFigure.find as Line).finalNode.x,
                (attachedFigure.find as Line).finalNode.y,
                mPaintLineMark
            )
        }

        for (node in attachedFigure.mNodes) {
            canvas.drawCircle(node.x, node.y, POINT_RADIUS, mPaintNode)
        }

        if (attachedFigure.find is Node) {
            canvas.drawCircle(
                (attachedFigure.find as Node).x,
                (attachedFigure.find as Node).y,
                POINT_RADIUS,
                mPaintNodeMark
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val mx = event.x
        val my = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> onTouchDown?.invoke(mx, my)
            MotionEvent.ACTION_MOVE -> onTouchMove?.invoke(mx, my)
            MotionEvent.ACTION_UP -> onTouchUp?.invoke(mx, my)
        }

        invalidate()
        return true
    }

    fun attachFigure(figure: Figure) {
        attachedFigure = figure
    }
}