package open.geosolve.geosolve.view.views.recyclers.items

import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.item_example_figure.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.fragments.RuleFragmentDirections
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter
import java.io.Serializable
import java.io.SerializablePermission
import java.lang.invoke.LambdaConversionException

class ExampleFigureItem(private val dataCanvas: CanvasData, private val updateForString: () -> CharSequence) : RuleItem {
    override val layoutRes = R.layout.item_example_figure

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.exampleCanvasView.dataCanvas = dataCanvas

        holder.itemView.open_example_figure.setOnClickListener {
            val lambda = updateForString as Serializable
            val cloneData = dataCanvas.clone() as CanvasData // TODO(make clone dataCanvas)

            val action = RuleFragmentDirections.actionToExampleFigure(cloneData, lambda)

            findNavController(holder.itemView).navigate(action)
        }
    }
}