package open.geosolve.geosolve.view.views.recyclers

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_article.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.fragments.BookFragmentDirections
import open.geosolve.geosolve.view.fragments.SolveFragmentDirections
import open.geosolve.geosolve.view.inflate
import open.geosolve.geosolve.view.rules.Article
import open.geosolve.geosolve.view.rules.RectangleRules
import open.geosolve.geosolve.view.rules.RightTriangleRules
import open.geosolve.geosolve.view.rules.TriangleRules
import open.geosolve.geosolve.view.views.recyclers.items.TitleItem

class BookRecyclerAdapter : RecyclerView.Adapter<BookRecyclerAdapter.BookHolder>() {

    private val articlesList: MutableList<Article> = mutableListOf( // TODO(i don't know what did with that)
        TriangleRules.know_2_unknown_1_angle,
        RightTriangleRules.angle_30_degrees,
        RightTriangleRules.pythagorean_theorem,
        RectangleRules.parallel_line,
        RectangleRules.right_angles
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
