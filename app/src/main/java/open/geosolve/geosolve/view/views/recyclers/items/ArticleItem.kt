package open.geosolve.geosolve.view.views.recyclers.items

import open.geosolve.geosolve.view.views.recyclers.ArticleRecyclerAdapter

interface ArticleItem {
    val layoutRes: Int
    fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder)
}