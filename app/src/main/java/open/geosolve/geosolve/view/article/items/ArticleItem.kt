package open.geosolve.geosolve.view.article.items

import open.geosolve.geosolve.view.article.ArticleRecyclerAdapter

interface ArticleItem {
    val layoutRes: Int
    fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder)
}