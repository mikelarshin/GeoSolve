package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.formatExpression
import open.geosolve.geosolve.view.formatFormula
import open.geosolve.geosolve.view.formatVerbal
import open.geosolve.geosolve.view.views.recyclers.items.EndItem
import open.geosolve.geosolve.view.views.recyclers.items.RuleItem
import open.geosolve.geosolve.view.views.recyclers.items.TextItem
import open.geosolve.geosolve.view.views.recyclers.items.TitleItem
import java.io.Serializable

abstract class Rule : Serializable {
    abstract val verbalID: Int
    abstract val expressionID: Int

    abstract val verbalOrder: List<SolveGraph>
    abstract val expressionOrder: List<SolveGraph>

    open val ruleItems: List<RuleItem> = listOf(
        TitleItem(R.string.ruleTitle_rectangle_parallel_line),
        TextItem(R.string.ruleText_rectangle_parallel_line),
        EndItem()
    )

    val verbal: CharSequence
        get() = formatVerbal(this)
    val formula: CharSequence
        get() = formatFormula(this)
    val expression: CharSequence
        get() = formatExpression(this)
}