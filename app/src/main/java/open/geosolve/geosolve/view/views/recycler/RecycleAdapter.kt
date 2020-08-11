package open.geosolve.geosolve.view.views.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_step.view.*
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.DesignUtil.formatAnswer
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_step, parent, false)
        return RecycleViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = holder.itemView

        val stepSolve = stepSolveList[position]

        item.setOnClickListener {
            if (holder.isOpen)
                holder.close_item()
            else
                holder.open_item()
        }

        item.formula.text = stepSolve.getFormula()
        item.verbal.text = stepSolve.getVerbal()
        item.expression.text = stepSolve.getExpression()

        if (position + 1 == itemCount) { // если это последний item
            item.openButton.visibility = View.GONE
            item.formula.text = formatAnswer(stepSolve)

            item.setOnClickListener {} // off
        }

        item.read_rule_button.setOnClickListener {
            it.findNavController().navigate(R.id.action_to_rule)
        }
    }

    override fun getItemCount(): Int {
        return stepSolveList.size
    }
}