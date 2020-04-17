package com.example.geosolve.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.geosolve.MainActivity
import com.example.geosolve.Presenter

class CanvasView : View {

    var presenter: Presenter = MainActivity.presenter

    // Чтобы View можно было создавать в разметке
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        presenter.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val mx = event.x
        val my = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> presenter.onTouchDown(mx, my)

            MotionEvent.ACTION_MOVE -> presenter.onTouchMove(mx, my)

            MotionEvent.ACTION_UP ->   presenter.onTouchUp(mx, my)
        }

        invalidate()
        return true
    }
}