package open.geosolve.geosolve.view.screens.solveScreen

import android.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_step.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatAnswer
import java.util.*

class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {

    companion object {
        private var stepSolveList: MutableList<Element> = ArrayList()

        fun addItem(stepSolve: Element) {
            stepSolveList.add(stepSolve)
        }

        fun clear() {
            stepSolveList.clear()
        }

        fun addAll(steps: List<Element>) {
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

        if (position + 1 == stepSolveList.size) {
            item.openButton.visibility = View.GONE
            item.formula.text = formatAnswer(stepSolveList[position])
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
                    it.openButton.setImageResource(R.drawable.ic_close)
                    holder.isOpen = true
                }
            }

            item.formula.text = stepSolveList[position].getFormula()
            item.verbal.text = stepSolveList[position].getVerbal()
            item.expression.text = stepSolveList[position].getExpression()
        }
    }

    override fun getItemCount(): Int {
        return stepSolveList.size
    }
}