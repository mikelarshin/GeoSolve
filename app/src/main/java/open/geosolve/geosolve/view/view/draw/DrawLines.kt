package open.geosolve.geosolve.view.view.draw

import android.graphics.Canvas
import android.graphics.Paint
import open.geosolve.geosolve.App.Companion.allLines
import open.geosolve.geosolve.App.Companion.find
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.view.screens.DesignUtil.formatValueString
import open.geosolve.geosolve.view.view.draw.PaintConstant.TEXT_SIZE
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintLine
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintLineMark
import open.geosolve.geosolve.view.view.draw.PaintConstant.mPaintText

object DrawLines {
    fun drawLines(canvas: Canvas) {
        for (line in allLines)
            drawLine(canvas, line)
    }

    fun drawLinesMark(canvas: Canvas) {
        if (find !is Line) return
        val markedLine = find as Line
        drawLine(canvas, markedLine, mPaintLineMark)
    }

    fun drawLinesValue(canvas: Canvas) {
        for (line in allLines) {
            if (line.getValue() == null) continue

            val text = formatValueString(line)

            val centerX = (line.startNode.x + line.finalNode.x) / 2 - TEXT_SIZE * text.length / 3.5f
            val centerY = (line.startNode.y + line.finalNode.y) / 2 + TEXT_SIZE / 3.5f

            canvas.drawText(text, centerX, centerY, mPaintText)
        }
    }

    private fun drawLine(canvas: Canvas, line: Line, mPaint: Paint = mPaintLine) {
        canvas.drawLine(
            line.startNode.x, line.startNode.y,
            line.finalNode.x, line.finalNode.y,
            mPaint
        )
    }
}