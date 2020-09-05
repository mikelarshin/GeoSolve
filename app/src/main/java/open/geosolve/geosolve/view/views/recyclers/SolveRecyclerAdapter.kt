package open.geosolve.geosolve.view.views.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_solve.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.formatAnswer
import open.geosolve.geosolve.view.fragments.SolveFragmentDirections

class SolveRecyclerAdapter : RecyclerView.Adapter<SolveRecyclerAdapter.SolveRecycleHolder>() {

    private val stepSolveList: MutableList<SolveGraph> = mutableListOf()

    fun fill(addList: List<SolveGraph>) {
        stepSolveList.clear()
        stepSolveList.addAll(addList)
    }

    class SolveRecycleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isOpen = false
        private val itemParams = itemView.formula.layoutParams as ConstraintLayout.LayoutParams

        fun open_item() {
            itemView.rule_layout.visibility = View.VISIBLE
            itemParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET
            itemView.openButton.setImageResource(R.drawable.ic_close)
            isOpen = true
        }

        fun close_item() {
            itemView.rule_layout.visibility = View.GONE
            itemParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            itemView.openButton.setImageResource(R.drawable.ic_arrow_down)
            isOpen = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolveRecycleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_solve, parent, false)
        return SolveRecycleHolder(view)
    }

    override fun onBindViewHolder(holder: SolveRecycleHolder, position: Int) {
        val item = holder.itemView

        val stepSolve = stepSolveList[position]

        item.setOnClickListener {
            if (holder.isOpen)
                holder.close_item()
            else
                holder.open_item()
        }

        item.formula.text = stepSolve.rule.formula
        item.verbal.text = stepSolve.rule.verbal
        item.expression.text = stepSolve.rule.expression

        if (position + 1 == itemCount) { // если это последний item
            item.openButton.visibility = View.GONE
            item.formula.text = formatAnswer(stepSolve)

            item.setOnClickListener {} // off
        }

        item.read_rule_button.setOnClickListener {
            val action = SolveFragmentDirections.actionToRule(stepSolve.rule)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = stepSolveList.size
}