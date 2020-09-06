package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.item_sub_title.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.getText
import open.geosolve.geosolve.view.views.recyclers.ArticleRecyclerAdapter

class SubTitleItem(textID: Int) : ArticleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_sub_title

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.rule_sub_title.text = text
    }
}