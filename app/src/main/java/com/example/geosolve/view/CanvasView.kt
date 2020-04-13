package com.example.geosolve.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.geosolve.status.Mode
import com.example.geosolve.model.Figure

class CanvasView : View {

    companion object {
        private var figure: Figure = Figure()
        private var mPaintNode: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        private var mPaintLine: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

        init {
            mPaintNode.strokeWidth = 10f
            mPaintNode.color = Color.BLACK

            mPaintLine.strokeWidth = 5f
            mPaintLine.color = Color.parseColor("#d0d0d0")
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (figure.nodes.size > 1) {
            val nodes = figure.nodes
            for (i in 0 until nodes.size - 1) {
                canvas.drawLine(
                    nodes[i].x,
                    nodes[i].y,
                    nodes[i + 1].x,
                    nodes[i + 1].y,
                    mPaintLine
                )
            }
        }

        for (node in figure.nodes) {
            canvas.drawCircle(node.x, node.y, 20f, mPaintNode)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val mx = event.x
        val my = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                figure.setState(mx, my)
            }
            MotionEvent.ACTION_MOVE -> {
                figure.moveNode(mx, my)
            }
            MotionEvent.ACTION_UP -> {
                figure.addNodeIf(mx, my)
            }
        }

        invalidate()
        return true
    }

    fun clear() {
        figure = Figure()
        invalidate()
    }

    fun setMode(mode: Mode) {
        figure.mode = mode
    }
}