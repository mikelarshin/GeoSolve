package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line

open class DrawCanvasView : View {

    companion object {
        private const val POINT_RADIUS: Float = 20f
        private const val LINE_WIDTH: Float = 5f
        private const val TEXT_SIZE: Float = 42f
    }

    private val mPaintNode = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_node)
        strokeWidth = POINT_RADIUS
    }
    private val mPaintNodeMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_mark)
        strokeWidth = POINT_RADIUS
    }
    private val mPaintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_line)
        strokeWidth = LINE_WIDTH
    }
    private val mPaintLineMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_mark)
        strokeWidth = LINE_WIDTH
    }
    private val mPaintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.canvas_text_color)
        textSize = TEXT_SIZE
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var attachedFigure: Figure

    fun attachFigure(figure: Figure) {
        attachedFigure = figure
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawLines(canvas)
        drawValueLines(canvas)
        drawMarkedLine(canvas)

        drawNodes(canvas)
        drawNodesName(canvas)

//        drawAngleCircle()
        drawValueAngle(canvas)
        drawMarkedAngle(canvas)
    }

    private fun drawLines(canvas: Canvas) {
        for (line in attachedFigure.mLines)
            canvas.drawLine(
                line.startNode.x, line.startNode.y,
                line.finalNode.x, line.finalNode.y,
                mPaintLine
            )
    }

    private fun drawNodes(canvas: Canvas) {
        for (node in attachedFigure.mNodes)
            canvas.drawCircle(node.x, node.y, POINT_RADIUS, mPaintNode)
    }

    private fun drawMarkedLine(canvas: Canvas) {
        if (attachedFigure.find !is Line) return

        val markedLine = attachedFigure.find as Line

        canvas.drawLine(
            markedLine.startNode.x,
            markedLine.startNode.y,
            markedLine.finalNode.x,
            markedLine.finalNode.y,
            mPaintLineMark
        )
    }

    private fun drawMarkedAngle(canvas: Canvas) {
        if (attachedFigure.find !is Angle) return

        val markedAngle = attachedFigure.find as Angle

        canvas.drawCircle(
            markedAngle.angleNode.x,
            markedAngle.angleNode.y,
            POINT_RADIUS,
            mPaintNodeMark
        )
    }

    private fun drawNodesName(canvas: Canvas) {
        for (node in attachedFigure.mNodes) {
            canvas.drawText(
                node.char.toString(),
                node.x - 50,
                node.y - 50,
                mPaintText
            )
        }
    }

    // TODO Рисовать дугу угла fun drawAngleCircle()
    // TODO Подстраивать положение текста, чтобы не наезжал на линии
    private fun drawValueAngle(canvas: Canvas) {
        for (angle in attachedFigure.mAngles) {
            if (angle.getValue() == null) continue

            canvas.drawText(
                getNormalizeValue(angle.getValue()!!),
                angle.angleNode.x + 50,
                angle.angleNode.y + 50,
                mPaintText
            )
        }
    }

    private fun drawValueLines(canvas: Canvas) {
        for (line in attachedFigure.mLines) {
            if (line.getValue() == null) continue

            canvas.drawText(
                getNormalizeValue(line.getValue()!!),
                (line.startNode.x + line.finalNode.x) / 2,
                (line.startNode.y + line.finalNode.y) / 2,
                mPaintText
            )//TODO(выровнять относительно размера текста)
        }
    }

    private fun getNormalizeValue(value: Float) =
        (if (value - value.toInt() == 0f) value.toInt() else value).toString()
}