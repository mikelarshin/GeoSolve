package open.geosolve.geosolve.view.article.items

import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.item_example_figure.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeCanvasData
import open.geosolve.geosolve.model.canvas.tools.Tool
import open.geosolve.geosolve.view.article.ArticleFragmentDirections.actionToExampleFigure
import open.geosolve.geosolve.view.article.ArticleRecyclerAdapter
import java.io.Serializable

class ExampleFigureItem(private val dataCanvasFactory: () -> CanvasData, private val updateForString: () -> CharSequence, private val tool: Tool) : ArticleItem {
    override val layoutRes = R.layout.item_example_figure

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.exampleCanvasView.dataCanvas = dataCanvasFactory()

        holder.itemView.open_example_figure.setOnClickListener {
            val lambda = updateForString as Serializable
            val cloneData = dataCanvasFactory()

            val action = actionToExampleFigure(cloneData, lambda, tool)

            findNavController(holder.itemView).navigate(action)
        }
    }
}