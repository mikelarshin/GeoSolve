package open.geosolve.geosolve.ui.canvas.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import open.v0gdump.field.InteractiveFieldView

/*
 * TODO Рисовать недостающие части фигуры
 */

class FigureFieldView : InteractiveFieldView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.drawGrid(canvas)
        super.drawNotations(canvas)
    }
}