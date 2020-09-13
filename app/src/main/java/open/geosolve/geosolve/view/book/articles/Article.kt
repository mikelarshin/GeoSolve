package open.geosolve.geosolve.view.book.articles

import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.article.items.ArticleItem
import open.geosolve.geosolve.view.article.items.EndItem
import open.geosolve.geosolve.view.article.items.TextItem
import open.geosolve.geosolve.view.article.items.TitleItem
import java.io.Serializable

abstract class Article : Serializable {
    open val articleItems: List<ArticleItem> = listOf(
        TitleItem(R.string.in_progress),
        TextItem(R.string.in_progress),
        EndItem()
    )
}