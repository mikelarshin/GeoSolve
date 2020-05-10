package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import open.geosolve.geosolve.App
import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatValueString


open class DrawCanvasView : View {

    companion object {
        private fun getDimen(dimenId: Int) = App.instance.resources.getDimension(dimenId)

        val POINT_SIZE: Float = getDimen(R.dimen.POINT_SIZE)
        private val LINE_WIDTH: Float = getDimen(R.dimen.LINE_WIDTH)
        private val TEXT_SIZE: Float = getDimen(R.dimen.TEXT_SIZE)
        private val TEXT_MARGIN = getDimen(R.dimen.TEXT_MARGIN)
        private val ANGLE_ARC_RADIUS = getDimen(R.dimen.ANGLE_ARC_RADIUS)
    }

    private val mPaintNode = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_node)
        strokeWidth = POINT_SIZE
    }
    private val mPaintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_line)
        strokeWidth = LINE_WIDTH
    }
    private val mPaintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_circle)
        style = Paint.Style.STROKE
        strokeWidth = LINE_WIDTH
    }
    private val mPaintNodeMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_mark)
        strokeWidth = POINT_SIZE
    }
    private val mPaintLineMark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_mark)
        strokeWidth = LINE_WIDTH
    }
    private val mPaintMarkCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_mark)
        style = Paint.Style.STROKE
        strokeWidth = LINE_WIDTH
    }
    private val mPaintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.canvas_text_color)
        textSize = TEXT_SIZE
    }
    private val mPaintAngle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_angle_arc)
        strokeWidth = LINE_WIDTH
        style = Paint.Style.STROKE
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        App.systemCoordinate = SystemCoordinate.ABSOLUTE

        drawAngleCircle(canvas)
        drawLines(canvas)
        drawCircles(canvas)

        drawMarkedLine(canvas)
        drawMarkedCircle(canvas)

        drawNodes(canvas)
        drawMarkedAngle(canvas)

        drawValueAngle(canvas)
        drawValueLines(canvas)
        drawNodesName(canvas)

        App.systemCoordinate = SystemCoordinate.DECART
    }

    private fun drawLines(canvas: Canvas) {
        for (line in allLines)
            if (line != find)
                canvas.drawLine(
                    line.startNode.x, line.startNode.y,
                    line.finalNode.x, line.finalNode.y,
                    mPaintLine
                )
    }

    private fun drawNodes(canvas: Canvas) {
        for (node in allNodes)
            canvas.drawCircle(node.x, node.y, POINT_SIZE, mPaintNode)
    }

    private fun drawCircles(canvas: Canvas) {
        for (circle in allCircles) {

            canvas.drawCircle(
                circle.centerNode.x,
                circle.centerNode.y,
                circle.getDrawRadius(),
                mPaintCircle
            )

            canvas.drawCircle(
                circle.centerNode.x,
                circle.centerNode.y,
                POINT_SIZE,
                mPaintNode
            )
        }
    }

    private fun drawMarkedCircle(canvas: Canvas) {
        if (find !is Circle) return

        val markedCircle = find as Circle

        canvas.drawCircle(
            markedCircle.centerNode.x,
            markedCircle.centerNode.y,
            markedCircle.getDrawRadius(),
            mPaintMarkCircle
        )
    }

    private fun drawMarkedLine(canvas: Canvas) {
        if (find !is Line) return

        val markedLine = find as Line

        canvas.drawLine(
            markedLine.startNode.x,
            markedLine.startNode.y,
            markedLine.finalNode.x,
            markedLine.finalNode.y,
            mPaintLineMark
        )
    }

    private fun drawMarkedAngle(canvas: Canvas) {
        if (find !is Angle) return

        val markedAngle = find as Angle

        canvas.drawCircle(
            markedAngle.angleNode.x,
            markedAngle.angleNode.y,
            POINT_SIZE,
            mPaintNodeMark
        )
    }

    private fun drawNodesName(canvas: Canvas) {
        for (node in allNodes) {
            canvas.drawText(
                node.char.toString(),
                node.x - TEXT_MARGIN,
                node.y - TEXT_MARGIN,
                mPaintText
            )
        }
    }

    private fun drawValueLines(canvas: Canvas) {
        for (line in allLines) {
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

    private fun drawAngleCircle(canvas: Canvas) {
        for (angle in allAngles) {
//
//            val centerX = angle.angleNode.x
//            val centerY = angle.angleNode.y
//
//            val oval = RectF(
//                centerX - ANGLE_ARC_RADIUS,
//                centerY - ANGLE_ARC_RADIUS,
//                centerX + ANGLE_ARC_RADIUS,
//                centerY + ANGLE_ARC_RADIUS) TODO(Сделать отрисовку углов)
//
//            val start = (360 / Math.PI * atan2(angle.finalNode.y - angle.startNode.y, angle.finalNode.y - angle.startNode.x)).toFloat()
//
//            canvas.drawArc(oval, start, -100f, false, mPaintAngle)
        }
    }

    private fun drawValueAngle(canvas: Canvas) {
        for (angle in allAngles) {
            if (angle.getValue() == null) continue

            canvas.drawText(
                formatValueString(angle),
                angle.angleNode.x + TEXT_MARGIN,
                angle.angleNode.y + TEXT_MARGIN,
                mPaintText
            )
        }
    }
}
