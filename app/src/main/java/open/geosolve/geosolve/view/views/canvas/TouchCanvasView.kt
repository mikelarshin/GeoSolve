package open.geosolve.geosolve.view.views.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.views.canvas.draw.xToDecart
import open.geosolve.geosolve.view.views.canvas.draw.yToDecart
import kotlin.properties.Delegates


class TouchCanvasView : DrawCanvasView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs?.let {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.CanvasView)
            touchEnabled = attributes.getBoolean(R.styleable.CanvasView_touchEnabled, true)

            attributes.recycle()
        }
    }

    private var touchEnabled by Delegates.notNull<Boolean>()

    lateinit var onTouchDown: (x: Float, y: Float) -> Unit
    lateinit var onTouchMove: (x: Float, y: Float) -> Unit
    lateinit var onTouchUp: (x: Float, y: Float) -> Unit

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!touchEnabled) return true

        val mx = xToDecart(event.x)
        val my = yToDecart(event.y)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> onTouchDown.invoke(mx, my)
            MotionEvent.ACTION_MOVE -> onTouchMove.invoke(mx, my)
            MotionEvent.ACTION_UP -> onTouchUp.invoke(mx, my)
        }

        invalidate()
        return true
    }
}