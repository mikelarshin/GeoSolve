package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.exemple_figure_item.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.tools.MoveTool
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class ExempleFigureItem(private val dataCanvas: CanvasData) : RuleItem {
    override val layoutRes = R.layout.exemple_figure_item

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.exempleCanvasView.dataCanvas = dataCanvas
        holder.itemView.open_exemple_figure.setOnClickListener {

        }
    }
}