package open.geosolve.geosolve.ui.canvas

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.dialog_input_value.*
import kotlinx.android.synthetic.main.fragment_canvas.*
import kotlinx.android.synthetic.main.fragment_canvas.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.helper.FigureManipulator
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.canvas.CanvasScreenPresenter
import open.geosolve.geosolve.presentation.canvas.CanvasScreenView
import open.geosolve.geosolve.ui.global.MvpFragmentX
import open.v0gdump.field.InteractiveFieldCallback
import kotlin.math.roundToInt


class CanvasFragment : MvpFragmentX(R.layout.fragment_canvas), CanvasScreenView {

    private val presenter by moxyPresenter { CanvasScreenPresenter(app) }

    private val tools = listOf(
        ToolAbstraction(
            R.string.tool_pen,
            R.drawable.ic_tool_pen
        ),
        ToolAbstraction(
            R.string.tool_eraser,
            R.drawable.ic_tool_eraser
        ),
        ToolAbstraction(
            R.string.tool_set_value,
            R.drawable.ic_tool_set_value
        ),
        ToolAbstraction(
            R.string.tool_mark,
            R.drawable.ic_tool_mark
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FigureManipulator.linkFigure(app.figure)
    }

    override fun setupLayout() {
        layout.field.attach(app.figure)
        layout.field.callback = object : InteractiveFieldCallback {

            override fun isUsedByContent(x: Float, y: Float): Boolean {
                return presenter.isUsedByContent(x, y)
            }

            override fun onMove(x: Float, y: Float) {
                presenter.onMove(x, y)
            }

            override fun onMoveFinished(x: Float, y: Float) {
                presenter.onMoveFinished(x, y)
            }

            override fun onMoveStart(x: Float, y: Float) {
                presenter.onMoveStart(x, y)
            }

            override fun onTouch(x: Float, y: Float) {
                presenter.onTouch(x, y)
            }

        }

        tools.forEachIndexed { index, tool ->
            layout.tool_container.addView(
                FloatingActionButton(activity).apply {
                    layoutParams = LinearLayoutCompat.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, dpToPx(8), 0, dpToPx(8))
                    }

                    setImageResource(tool.icon)

                    setOnClickListener {
                        presenter.selectedTool = index
                        updateColor()
                    }
                }
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateColor()
    }

    private fun updateColor() {
        for (i in 0 until tool_container.childCount) {
            tool_container.getChildAt(i).backgroundTintList =
                if (presenter.selectedTool == i) {
                    ColorStateList.valueOf(resources.getColor(R.color.color_selected))
                } else {
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                }
        }
    }

    private fun dpToPx(dp: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).roundToInt()

    override fun updateCanvas() = field.invalidate()

    override fun showInputDialog(
        @StringRes titleRes: Int,
        callback: (value: Float) -> Unit
    ) {
        AlertDialog.Builder(activity)
            .setTitle(getText(titleRes))
            .setView(
                LayoutInflater.from(activity).inflate(R.layout.dialog_input_value, null)
            )
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                if (!(dialog as AlertDialog).input.text.isNullOrBlank()) {
                    callback(dialog.input.text.toString().toFloat())
                }
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun showFigureType() {

        val typeFigure =
            if (SolveUtil.typeSolve::class.simpleName != "UnknownFigure")
                SolveUtil.typeSolve::class.simpleName
            else
                "Неизвестная фигура"

        val subTypeFigure =
            if (SolveUtil.subTypeSolve::class.simpleName != "UnknownFigure")
                SolveUtil.subTypeSolve::class.simpleName
            else
                ""

        text_figure_type.text = if (subTypeFigure?.isNotEmpty() == true)
            "$typeFigure : $subTypeFigure"
        else
            typeFigure
    }
}
