package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import open.geosolve.geosolve.App.Companion.allAngles
import open.geosolve.geosolve.App.Companion.allCircles
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.allNodes
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.App.Companion.systemCoordinate
import open.geosolve.geosolve.model.MathUtil.getAngle
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatValueString
import open.geosolve.geosolve.view.view.PaintConstant.ANGLE_ARC_RADIUS
import open.geosolve.geosolve.view.view.PaintConstant.POINT_SIZE
import open.geosolve.geosolve.view.view.PaintConstant.TEXT_MARGIN
import open.geosolve.geosolve.view.view.PaintConstant.TEXT_SIZE
import open.geosolve.geosolve.view.view.PaintConstant.mPaintAngle
import open.geosolve.geosolve.view.view.PaintConstant.mPaintCircle
import open.geosolve.geosolve.view.view.PaintConstant.mPaintLine
import open.geosolve.geosolve.view.view.PaintConstant.mPaintLineMark
import open.geosolve.geosolve.view.view.PaintConstant.mPaintMarkCircle
import open.geosolve.geosolve.view.view.PaintConstant.mPaintNode
import open.geosolve.geosolve.view.view.PaintConstant.mPaintNodeMark
import open.geosolve.geosolve.view.view.PaintConstant.mPaintText


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

        systemCoordinate = SystemCoordinate.DECART
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
            val centerNode = circle.centerNode

            canvas.drawCircle(
                centerNode.x,
                centerNode.y,
                circle.drawRadius,
                mPaintCircle
            )

            canvas.drawCircle(
                centerNode.x,
                centerNode.y,
                POINT_SIZE,
                mPaintNode
            )

            canvas.drawText(
                centerNode.char.toString(),
                centerNode.x - TEXT_MARGIN,
                centerNode.y - TEXT_MARGIN,
                mPaintText
            )
        }
    }

    private fun drawMarkedCircle(canvas: Canvas) {
        if (find !is Circle) return

        val markedCircle = find as Circle

        canvas.drawCircle(
            markedCircle.centerNode.x,
            markedCircle.centerNode.y,
            markedCircle.drawRadius,
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
            )
        }
    }

    private fun drawAngleCircle(canvas: Canvas) {
        for (angle in allAngles) {

            val centerX = angle.angleNode.x
            val centerY = angle.angleNode.y

            // Данный код деактивирован до появления блокировки перемещения угла
            if (angle.getValue() == 90f && false) {
                val rectDraw = RectF(
                    centerX,
                    centerY - ANGLE_ARC_RADIUS,
                    centerX + ANGLE_ARC_RADIUS,
                    centerY
                )

                canvas.drawRect(rectDraw, mPaintAngle)
            } else {

                val rectDraw = RectF(
                    centerX - ANGLE_ARC_RADIUS,
                    centerY - ANGLE_ARC_RADIUS,
                    centerX + ANGLE_ARC_RADIUS,
                    centerY + ANGLE_ARC_RADIUS
                )

                val sweepAngle = getAngle(angle.startNode, angle.angleNode, angle.finalNode)

                systemCoordinate = SystemCoordinate.DECART
                val startNode = Node(angle.angleNode.x, angle.angleNode.y + 1f) // TODO(Replace Node with MathPoint)
                systemCoordinate = SystemCoordinate.ABSOLUTE

                val startAngle = getAngle(startNode, angle.angleNode, angle.startNode) - 90f

                canvas.drawArc(rectDraw, startAngle, sweepAngle, false, mPaintAngle)
            }
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
