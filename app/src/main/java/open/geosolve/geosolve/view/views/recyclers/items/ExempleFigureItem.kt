package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.exemple_figure_item.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class ExempleFigureItem : RuleItem {
    override val layoutRes = R.layout.exemple_figure_item

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.exempleTouchCanvasView.touchEnabled = false
    }
}