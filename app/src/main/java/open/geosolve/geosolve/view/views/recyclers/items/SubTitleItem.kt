package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.sub_title_item.view.*
import kotlinx.android.synthetic.main.text_item.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.DesignUtil.getText
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class SubTitleItem(textID: Int) : RuleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.sub_title_item

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.rule_sub_title.text = text
    }
}