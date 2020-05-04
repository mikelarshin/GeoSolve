package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatValueString

open class DrawCanvasView : View {

    companion object {
        const val POINT_SIZE: Float = 20f
        private const val LINE_WIDTH: Float = 5f
        private const val TEXT_SIZE: Float = 40f
        private lateinit var attachedFigure: Figure
        private val TEXT_MARGIN = 30f
    }

    private val mPaintNode = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_node)
        strokeWidth = POINT_SIZE
    }
    private val mPaintNodeMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_mark)
        strokeWidth = POINT_SIZE
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
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun attachFigure(figure: Figure) {
        attachedFigure = figure
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        App.systemCoordinate = SystemCoordinate.ABSOLUTE

        drawLines(canvas)
        drawMarkedLine(canvas)
        drawValueLines(canvas)

        drawNodes(canvas)
        drawNodesName(canvas)

//        drawAngleCircle()
        drawValueAngle(canvas)
        drawMarkedAngle(canvas)

        App.systemCoordinate = SystemCoordinate.DECART
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
            canvas.drawCircle(node.x, node.y, POINT_SIZE, mPaintNode)
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
            POINT_SIZE,
            mPaintNodeMark
        )
    }

    private fun drawNodesName(canvas: Canvas) {
        for (node in attachedFigure.mNodes) {
            canvas.drawText(
                node.char.toString(),
                node.x - TEXT_MARGIN,
                node.y - TEXT_MARGIN,
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
                formatValueString(angle),
                angle.angleNode.x + TEXT_MARGIN,
                angle.angleNode.y + TEXT_MARGIN,
                mPaintText
            )
        }
    }

    private fun drawValueLines(canvas: Canvas) {
        for (line in attachedFigure.mLines) {
            if (line.getValue() == null) continue

            val text = formatValueString(line)

            canvas.drawText(
                text,
                ((line.startNode.x + line.finalNode.x) / 2) - (TEXT_SIZE * text.length) / 3.5f,
                ((line.startNode.y + line.finalNode.y) / 2) + TEXT_SIZE / 3.5f,
                mPaintText
            )//TODO(выровнять относительно размера текста)
        }
    }
}