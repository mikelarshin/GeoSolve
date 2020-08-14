package open.geosolve.geosolve.view.views.recyclers.items

import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

interface RuleItem {
    val layoutRes: Int
    fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder)
}