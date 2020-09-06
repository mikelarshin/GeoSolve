package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.views.recyclers.items.ArticleItem
import open.geosolve.geosolve.view.views.recyclers.items.EndItem
import open.geosolve.geosolve.view.views.recyclers.items.TextItem
import open.geosolve.geosolve.view.views.recyclers.items.TitleItem
import java.io.Serializable

abstract class Article : Serializable {
    open val articleItems: List<ArticleItem> = listOf(
        TitleItem(R.string.in_progress),
        TextItem(R.string.in_progress),
        EndItem()
    )
}