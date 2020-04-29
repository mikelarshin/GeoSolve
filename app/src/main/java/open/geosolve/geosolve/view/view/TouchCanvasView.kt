package open.geosolve.geosolve.view.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

class TouchCanvasView : DrawCanvasView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var onTouchDown: ((x: Float, y: Float) -> Unit)? = null
    var onTouchMove: ((x: Float, y: Float) -> Unit)? = null
    var onTouchUp: ((x: Float, y: Float) -> Unit)? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val mx = event.x
        val my = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> onTouchDown?.invoke(mx, my)
            MotionEvent.ACTION_MOVE -> onTouchMove?.invoke(mx, my)
            MotionEvent.ACTION_UP -> onTouchUp?.invoke(mx, my)
        }

        invalidate()
        return true
    }
}