package open.geosolve.geosolve.view.views.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.inflate
import open.geosolve.geosolve.view.views.recyclers.items.RuleItem

class RuleRecyclerAdapter : RecyclerView.Adapter<RuleRecyclerAdapter.RuleRecycleHolder>() {

    private val itemList: MutableList<RuleItem> = mutableListOf()

    fun fill(addList: List<RuleItem>) {
        itemList.clear()
        itemList.addAll(addList)
    }

    class RuleRecycleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int) = itemList[position].layoutRes

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int) =
        RuleRecycleHolder(parent.inflate(layoutRes))


    override fun onBindViewHolder(holder: RuleRecycleHolder, position: Int) {
        val ruleItem = itemList[position]
        ruleItem.onBindViewHolder(holder)
    }

    override fun getItemCount() = itemList.size
}