package open.geosolve.geosolve.view.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.controllers.canvasData
import open.geosolve.geosolve.model.canvas.math.XYPoint
import open.geosolve.geosolve.presentation.canvas.CanvasPresenter
import open.geosolve.geosolve.view.canvas.draw.xToDecart
import open.geosolve.geosolve.view.canvas.draw.yToDecart
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
    val canvasPresenter = CanvasPresenter()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!touchEnabled) return true

        updateScale()
        val touchPoint = XYPoint(xToDecart(event.x), yToDecart(event.y))

        when (event.action) {
            MotionEvent.ACTION_DOWN -> canvasPresenter.onTouchDown(touchPoint)
            MotionEvent.ACTION_MOVE -> canvasPresenter.onTouchMove(touchPoint)
            MotionEvent.ACTION_UP -> canvasPresenter.onTouchUp(touchPoint)
        }

        invalidate()
        return true
    }
}