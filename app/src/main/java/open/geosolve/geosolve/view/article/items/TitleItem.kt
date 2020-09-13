package open.geosolve.geosolve.view.article.items

import kotlinx.android.synthetic.main.item_title.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.helpers.getText
import open.geosolve.geosolve.view.article.ArticleRecyclerAdapter

class TitleItem(textID: Int) : ArticleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_title

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.article_title.text = text
    }
}