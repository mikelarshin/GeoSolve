package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.item_text.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.getText
import open.geosolve.geosolve.view.views.recyclers.ArticleRecyclerAdapter


class TextItem(textID: Int) : ArticleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_text

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.rule_text.text = text
    }
}