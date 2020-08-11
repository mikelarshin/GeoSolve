package open.geosolve.geosolve.view.views.canvas.draw

import android.graphics.Canvas
import android.graphics.Paint
import open.geosolve.geosolve.AllLines
import open.geosolve.geosolve.GlobalFiguresController.find
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.view.DesignUtil.formatValueString
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.TEXT_SIZE
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintLine
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintLineMark
import open.geosolve.geosolve.view.views.canvas.draw.PaintConstant.mPaintText

object DrawLines {
    fun drawLines(canvas: Canvas) {
        for (line in AllLines)
            drawLine(canvas, line)
    }

    fun drawLinesMark(canvas: Canvas) {
        if (find !is Line) return
        val markedLine = find as Line
        drawLine(canvas, markedLine, mPaintLineMark)
    }

    fun drawLinesValue(canvas: Canvas) {
        for (line in AllLines) {
            if (line.getValue() == null) continue

            val text = formatValueString(line)

            val centerX = (line.firstNode.x + line.secondNode.x) / 2 - TEXT_SIZE * text.length / 3.5f
            val centerY = (line.firstNode.y + line.secondNode.y) / 2 + TEXT_SIZE / 3.5f

            canvas.drawText(text, centerX, centerY, mPaintText)
        }
    }

    private fun drawLine(canvas: Canvas, line: Line, mPaint: Paint = mPaintLine) {
        canvas.drawLine(
            line.firstNode.x, line.firstNode.y,
            line.secondNode.x, line.secondNode.y,
            mPaint
        )
    }
}