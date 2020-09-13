package open.geosolve.geosolve.view.article.items

import kotlinx.android.synthetic.main.item_text.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.helpers.getText
import open.geosolve.geosolve.view.article.ArticleRecyclerAdapter


class TextItem(textID: Int) : ArticleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_text

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.rule_text.text = text
    }
}