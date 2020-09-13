package open.geosolve.geosolve.view.article

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import open.geosolve.geosolve.view.helpers.inflate
import open.geosolve.geosolve.view.article.items.ArticleItem

class ArticleRecyclerAdapter : RecyclerView.Adapter<ArticleRecyclerAdapter.RuleHolder>() {

    private val articleItemList: MutableList<ArticleItem> = mutableListOf()

    fun fill(addList: List<ArticleItem>) {
        articleItemList.clear()
        articleItemList.addAll(addList)
    }

    class RuleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int) = articleItemList[position].layoutRes

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int) =
        RuleHolder(parent.inflate(layoutRes))


    override fun onBindViewHolder(holder: RuleHolder, position: Int) {
        val ruleItem = articleItemList[position]
        ruleItem.onBindViewHolder(holder)
    }

    override fun getItemCount() = articleItemList.size
}