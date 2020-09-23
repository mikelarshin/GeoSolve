package open.geosolve.geosolve.view.book.articles

import open.geosolve.geosolve.view.article.items.ArticleItem
import java.io.Serializable

abstract class Article : Serializable {
    abstract val articleItems: List<ArticleItem>
}