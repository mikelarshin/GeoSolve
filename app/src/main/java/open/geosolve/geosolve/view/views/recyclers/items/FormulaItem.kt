package open.geosolve.geosolve.view.views.recyclers.items

import kotlinx.android.synthetic.main.item_formula.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.getText
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class FormulaItem(textID: Int) : RuleItem {
    val text: String = getText(textID)

    override val layoutRes = R.layout.item_formula

    override fun onBindViewHolder(holder: RuleRecyclerAdapter.RuleRecycleHolder) {
        holder.itemView.rule_formula.text = text
    }
}