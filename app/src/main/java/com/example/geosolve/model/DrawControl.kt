package com.example.geosolve.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.geosolve.MainActivity
import com.example.geosolve.const_enum.Mode
import com.example.geosolve.const_enum.State
import com.example.geosolve.model.figure.Figure
import com.example.geosolve.model.figure.Line
import com.example.geosolve.model.figure.Node
import com.example.geosolve.view.CanvasView

class DrawControl(private val figure: Figure) {
    private val mPaintNode: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintLine: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintNodeMark: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintLineMark: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mode: Mode = Mode.ADD_MOVE_FIN
    private var state: State = State.ON_CANVAS
    private var numOfCall: Int = 0

    init {
        mPaintNode.strokeWidth = 10f
        mPaintNode.color = Color.BLACK

        mPaintNodeMark.strokeWidth = 10f
        mPaintNodeMark.color = Color.parseColor("#DF1616")

        mPaintLineMark.strokeWidth = 5f
        mPaintLineMark.color = Color.parseColor("#DF1616")

        mPaintLine.strokeWidth = 5f
        mPaintLine.color = Color.parseColor("#d0d0d0")
    }

    fun onDraw(canvas: Canvas) {

        for (line in figure.mLines) {
            canvas.drawLine(
                line.startNode.x, line.startNode.y,
                line.finNode.x, line.finNode.y,
                mPaintLine
            )
        }

        if (figure.find is Line)
            canvas.drawLine(
                (figure.find as Line).startNode.x, (figure.find as Line).startNode.y,
                (figure.find as Line).finNode.x, (figure.find as Line).finNode.y, mPaintLineMark
            )

        for (node in figure.mNodes) {
            canvas.drawCircle(node.x, node.y, 20f, mPaintNode)
        }

        if (figure.find is Node)
            canvas.drawCircle((figure.find as Node).x, (figure.find as Node).y, 20f, mPaintNodeMark)
    }

    fun onTouchDown(touchX: Float, touchY: Float) {
        for (node in figure.mNodes) {
            if (node.inRadius(touchX, touchY)) {
                node.isMove = true
                state = State.ON_POINT
                break
            }
        }
    }

    fun onTouchMove(touchX: Float, touchY: Float) {
        for (node in figure.mNodes) {
            if (node.isMove) {
                node.moveNode(touchX, touchY)
            }
        }
        numOfCall++
    }

    fun onTouchUp(touchX: Float, touchY: Float) {
        when (mode) {
            Mode.ADD_MOVE_FIN -> {
                when (state) {
                    State.ON_CANVAS -> {
                        figure.addNode(Node(touchX, touchY))
                        if (figure.mNodes.size > 1)
                            figure.addLine(
                                figure.mNodes[figure.mNodes.size - 2],
                                figure.mNodes.last()
                            )
                    }
                    State.ON_POINT -> if (numOfCall < 2)
                        for (node in figure.mNodes)
                            if (node.inRadius(touchX, touchY)) {
                                figure.addLine(figure.mNodes.last(), node)
                                break
                            }
                }
            }
            Mode.DEL_MOVE -> figure.delNode(touchX, touchY)
            Mode.MARK_FIND -> figure.find = figure.getInRadius(touchX, touchY) ?: figure.find
            Mode.SET_VAlUE -> MainActivity.presenter.onClickWithSet(
                figure.getInRadius(touchX, touchY))
        }

        numOfCall = 0
        state = State.ON_CANVAS
        figure.stopAllNode()
    }

    fun clearCanvas(canvasView: CanvasView) {
        figure.clearFigure()
        canvasView.invalidate()
    }
}