package open.geosolve.geosolve.ui.screens.solveScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_step.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.repository.model.StepSolve
import java.util.*

class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {

    companion object {
        private var stepSolveList: MutableList<StepSolve> = ArrayList()

        fun addItem(stepSolve: StepSolve) {
            stepSolveList.add(stepSolve)
        }

        fun clear() {
            stepSolveList.clear()
        }

        fun addAll(steps: List<StepSolve>?) {
            stepSolveList.addAll(steps!!)
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
        holder.itemView.setOnClickListener {
            if (holder.isOpen) {
                holder.itemView.rule_layout.visibility = View.GONE
                holder.itemView.openButton.setImageResource(R.drawable.ic_arrow_down)
                holder.isOpen = false
            } else {
                holder.itemView.rule_layout.visibility = View.VISIBLE
                holder.itemView.openButton.setImageResource(R.drawable.ic_close)
                holder.isOpen = true
            }
        }

        holder.itemView.expression.text = stepSolveList[position].expression
        holder.itemView.rule.text = stepSolveList[position].verbal
    }

    override fun getItemCount(): Int {
        return stepSolveList.size
    }
}