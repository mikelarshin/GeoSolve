package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.item_title.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.getText
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class TitleItem(textID: Int) : RuleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_title

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.rule_title.text = text
    }
}