package open.geosolve.geosolve.view.screens.solveScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_step.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.screens.DesignUtil.formatAnswer
import java.util.*

class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {

    companion object {
        private var stepSolveList: MutableList<SolveGraph> = ArrayList()

        fun clear() {
            stepSolveList.clear()
        }

        fun addAll(steps: List<SolveGraph>) {
            stepSolveList.addAll(steps)
        }
    }

    class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isOpen = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_step, parent, false)
        return RecycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = holder.itemView

        val stepSolve = stepSolveList[position]

        if (position + 1 == stepSolveList.size) {
            item.openButton.visibility = View.GONE
            item.formula.text = formatAnswer(stepSolve)
        } else {
            item.setOnClickListener {
                val itemParams = it.formula.layoutParams as ConstraintLayout.LayoutParams
                if (holder.isOpen) {
                    it.rule_layout.visibility = View.GONE
                    itemParams.bottomToBottom = 0
                    itemParams.bottomMargin = 8
                    it.openButton.setImageResource(R.drawable.ic_arrow_down)
                    holder.isOpen = false
                } else {
                    it.rule_layout.visibility = View.VISIBLE
                    itemParams.bottomToBottom = -1
                    it.openButton.setImageResource(R.drawable.ic_close_button)
                    holder.isOpen = true
                }
            }

            item.formula.text = stepSolve.getFormula()
            item.verbal.text = stepSolve.getVerbal()
            item.expression.text = stepSolve.getExpression()
        }
    }

    override fun getItemCount(): Int {
        return stepSolveList.size
    }
}