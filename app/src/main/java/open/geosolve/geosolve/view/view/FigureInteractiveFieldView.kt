package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Node
import open.v0gdump.field.InteractiveFieldView
import open.v0gdump.field.TextAnchor

class FigureInteractiveFieldView : InteractiveFieldView {

    //region Drawing data

    val pointRadius
        get() = 20 * scale

    val lineThickness
        get() = 1 * scale

    //endregion

    //region Paints

    private val paintNode = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_node)
        strokeWidth = pointRadius
    }

    private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_line)
        strokeWidth = lineThickness
    }

    private val paintAngle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_angle)
        textSize = lineThickness
    }

    //endregion

    //region Figure

    private lateinit var attachedFigure: Figure

    fun attach(figure: Figure) {
        attachedFigure = figure
    }

    //endregion

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.drawGrid(canvas)

        drawLines(canvas)
        drawNodes(canvas)

        super.drawNotations(canvas)
    }

    private fun drawLines(canvas: Canvas) {
        for (line in attachedFigure.mLines) {
            drawLine(
                canvas,
                line.startNode.x, line.startNode.y,
                line.finalNode.x, line.finalNode.y,
                paintLine
            )
        }
    }

    private fun drawNodes(canvas: Canvas) {
        attachedFigure.mNodes.forEach { node ->
            drawNode(canvas, node)
            drawNodesName(canvas)
        }
    }

    private fun drawNodesName(canvas: Canvas) {
        for (node in attachedFigure.mNodes) {
            drawText(
                node.char.toString(),
                canvas,
                node.x,
                node.y,
                paintNotations,
                TextAnchor.TopLeft,
                pointRadius
            )
        }
    }

    private fun drawNode(canvas: Canvas, node: Node) {
        drawCircle(canvas, node.x, node.y, pointRadius, paintNode)
    }
}