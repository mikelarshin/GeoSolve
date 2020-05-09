package open.geosolve.geosolve.ui.solve

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_step.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.ui.solve.DesignUtil.formatAnswer
import open.geosolve.geosolve.util.visible

/*
 * FIXME(CODE) Переписать код ViewHolder
 * TODO(CODE) Использовать для сворачивающихся блоков библиотеку
 */

class SolveStepsAdapter(
    private val solveSteps: List<Element>
) : RecyclerView.Adapter<SolveStepsAdapter.SolveStepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SolveStepViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_step, parent, false)
        )

    override fun onBindViewHolder(holder: SolveStepViewHolder, position: Int) =
        holder.bind(solveSteps[position], solveSteps.lastIndex == position)

    override fun getItemCount() =
        solveSteps.size

    inner class SolveStepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var isClosed = true

        fun bind(step: Element, isLast: Boolean) {

            if (isLast) {
                itemView.toggle_button.visible(false)
                itemView.description_group.visible(false)

                itemView.formula.text = formatAnswer(solveSteps[position])

                return
            }

            itemView.setOnClickListener {
                isClosed = !isClosed
                updateLayoutState()
            }

            itemView.formula.text = step.getFormula()
            itemView.verbal.text = step.getVerbal()
            itemView.expression.text = step.getExpression()

            updateLayoutState()
        }

        private fun updateLayoutState() {

            itemView.description_group.visible(!isClosed)

            itemView.toggle_button.setImageResource(
                if (isClosed) R.drawable.ic_arrow_down
                else R.drawable.ic_close
            )
        }
    }
}