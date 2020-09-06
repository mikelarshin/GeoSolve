package open.geosolve.geosolve.view.views.recyclers.items

import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.item_example_figure.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.tools.Tool
import open.geosolve.geosolve.view.fragments.ArticleFragmentDirections
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.ArticleRecyclerAdapter
import java.io.Serializable

class ExampleFigureItem(private val dataCanvasFactory: () -> CanvasData, private val updateForString: () -> CharSequence, private val tool: Tool) : ArticleItem {
    override val layoutRes = R.layout.item_example_figure

    override fun onBindViewHolder(holder: ArticleRecyclerAdapter.RuleHolder) {
        holder.itemView.exampleCanvasView.dataCanvas = dataCanvasFactory()

        holder.itemView.open_example_figure.setOnClickListener {
            val lambda = updateForString as Serializable
            val cloneData = dataCanvasFactory()

            val action = ArticleFragmentDirections.actionToExampleFigure(cloneData, lambda, tool)

            findNavController(holder.itemView).navigate(action)
        }
    }
}