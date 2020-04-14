package com.example.geosolve.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.geosolve.const_enum.Mode
import com.example.geosolve.const_enum.State
import com.example.geosolve.view.CanvasView

class DrawControl(private val figure: Figure) {
    private val mPaintNode: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintLine: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mode: Mode = Mode.ADDMOVE
    private var state: State = State.DID
    private var numOfCall: Int = 0

    init {
        mPaintNode.strokeWidth = 10f
        mPaintNode.color = Color.BLACK

        mPaintLine.strokeWidth = 5f
        mPaintLine.color = Color.parseColor("#d0d0d0")
    }

    fun onDraw(canvas: Canvas) {
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

    fun setState(touchX: Float, touchY: Float) {
        for (node in figure.nodes) {
            if (node.inRadius(touchX, touchY)) {
                node.isMove = true
                state = State.ONPOINT
                break
            }
        }
    }

    fun moveNode(touchX: Float, touchY: Float) {
        for (node in figure.nodes) {
            if (node.isMove) {
                node.moveNode(touchX, touchY)
            }
        }
        numOfCall++
    }

    fun addNodeIf(touchX: Float, touchY: Float) {
        when (mode) {
            Mode.ADDMOVE -> {
                if (state == State.DID)
                    figure.addNode(Node(touchX, touchY))
                else if ((state == State.ONPOINT) and (numOfCall < 2)) {
                    Log.e("tag", "finish the figure")               // finish the figure!!!
                }
            }
            Mode.DELMOVE -> figure.delNode(touchX, touchY)
        }

        numOfCall = 0
        state = State.DID
        figure.stopAllNode()
    }

    fun clearCanvas(canvasView: CanvasView){
        figure.clearFigure()
        canvasView.invalidate()
    }
}