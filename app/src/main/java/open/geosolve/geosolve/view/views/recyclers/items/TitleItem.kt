package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.title_item.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.DesignUtil.getText
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class TitleItem(textID: Int) : RuleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.title_item

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.rule_title.text = text
    }
}