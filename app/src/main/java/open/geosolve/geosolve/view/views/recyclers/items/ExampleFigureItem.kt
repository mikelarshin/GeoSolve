package open.geosolve.geosolve.view.views.recyclers.items

import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.item_example_figure.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.tools.Tool
import open.geosolve.geosolve.view.fragments.RuleFragmentDirections
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter
import java.io.Serializable
import java.io.SerializablePermission
import java.lang.invoke.LambdaConversionException

class ExampleFigureItem(private val dataCanvasFactory: () -> CanvasData, private val updateForString: () -> CharSequence, private val tool: Tool) : RuleItem {
    override val layoutRes = R.layout.item_example_figure

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.exampleCanvasView.dataCanvas = dataCanvasFactory()

        holder.itemView.open_example_figure.setOnClickListener {
            val lambda = updateForString as Serializable
            val cloneData = dataCanvasFactory() // TODO(make clone dataCanvas)

            val action = RuleFragmentDirections.actionToExampleFigure(cloneData, lambda, tool)

            findNavController(holder.itemView).navigate(action)
        }
    }
}