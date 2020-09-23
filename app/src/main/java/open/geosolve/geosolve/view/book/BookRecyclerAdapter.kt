package open.geosolve.geosolve.view.book

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_article.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.article.items.TitleItem
import open.geosolve.geosolve.view.book.articles.Article
import open.geosolve.geosolve.view.book.articles.polygon.quadrangle.ParallelLines
import open.geosolve.geosolve.view.book.articles.polygon.quadrangle.RightAngles
import open.geosolve.geosolve.view.book.articles.polygon.triangle.Angle30Degrees
import open.geosolve.geosolve.view.book.articles.polygon.triangle.TriangleAnglesSum
import open.geosolve.geosolve.view.book.articles.polygon.triangle.PythagoreanTheorem
import open.geosolve.geosolve.view.helpers.inflate

class BookRecyclerAdapter : RecyclerView.Adapter<BookRecyclerAdapter.BookHolder>() {

    val articlesList: MutableList<Article> = mutableListOf(
        TriangleAnglesSum,
        Angle30Degrees,
        PythagoreanTheorem,
        ParallelLines,
        RightAngles
    )

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookHolder(parent.inflate(R.layout.item_article))

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val item = holder.itemView
        val article = articlesList[position]

        item.article.text = (article.articleItems.find { it is TitleItem } as TitleItem).text
        item.article.setOnClickListener {
            val action = BookFragmentDirections.actionToArticle(article)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = articlesList.size
}
