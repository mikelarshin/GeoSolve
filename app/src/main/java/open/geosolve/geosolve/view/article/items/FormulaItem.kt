package open.geosolve.geosolve.view.article.items

import kotlinx.android.synthetic.main.item_formula.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.helpers.getText
import open.geosolve.geosolve.view.article.ArticleRecyclerAdapter

class FormulaItem(textID: Int) : ArticleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_formula

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.rule_formula.text = text
    }
}