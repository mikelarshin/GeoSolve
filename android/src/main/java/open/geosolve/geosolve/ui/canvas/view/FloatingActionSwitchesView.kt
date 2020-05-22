package open.geosolve.geosolve.ui.canvas.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import open.geosolve.geosolve.R
import kotlin.math.roundToInt

class FloatingActionSwitchesView : LinearLayoutCompat {

    var selectedSwitch: Int = 0
        set(value) {
            check(value >= 0) { "selectedSwitch should be more or equal 0. selectedSwitch = $selectedSwitch" }
            field = value
            updateHighlight()
        }

    var onSwitchSelected: ((Int) -> Unit)? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        orientation = VERTICAL
        clipToPadding = false
    }

    fun addSwitch(
        index: Int,
        @StringRes name: Int,
        @DrawableRes icon: Int
    ) = addView(
        FloatingActionButton(context).apply {

            check(index >= 0) { "index should be more or equal 0. Index = $index" }

            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, dpToPx(12))
            }

            setImageResource(icon)

            updateVisualState(this, index)

            setOnClickListener {
                selectedSwitch = index
                onSwitchSelected?.invoke(index)
            }
        }
    )

    private fun updateHighlight() {
        (0 until childCount).forEach { i -> updateVisualState(getChildAt(i), i) }
    }

    private fun updateVisualState(view: View, index: Int) {
        view.backgroundTintList =
            if (index == selectedSwitch) {
                ColorStateList.valueOf(resources.getColor(R.color.color_selected))
            } else {
                ColorStateList.valueOf(resources.getColor(R.color.color_unselected))
            }
    }

    private fun dpToPx(dp: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).roundToInt()
}