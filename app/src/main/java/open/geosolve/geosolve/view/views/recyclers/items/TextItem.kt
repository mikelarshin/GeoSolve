package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.text_item.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.DesignUtil.getText
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter


class TextItem(textID: Int) : RuleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.text_item

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.rule_text.text = text
    }
}