package com.example.geosolve.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geosolve.R
import com.example.geosolve.StepSlove
import kotlinx.android.synthetic.main.step_item.view.*
import java.util.*

class RecycleAdapter(context: Context?) : RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {

    companion object {
        private var stepSloveList: MutableList<StepSlove> = ArrayList()
        var context: Context? = null
            private set

        fun addItem(stepSlove: StepSlove) {
            stepSloveList.add(stepSlove)
        }

        fun clear() {
            stepSloveList.clear()
        }

        fun addAll(steps: List<StepSlove>?) {
            stepSloveList.addAll(steps!!)
        }
    }

    init {
        Companion.context = context
    }

    class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isOpen = false

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.step_item, parent, false)
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
        if (position == stepSloveList.size - 1)
            holder.itemView.findViewById<View>(R.id.rect).visibility = View.GONE

        holder.itemView.expression.text = stepSloveList[position].expression
        holder.itemView.rule.text = stepSloveList[position].rule
    }

    override fun getItemCount(): Int {
        return stepSloveList.size
    }
}