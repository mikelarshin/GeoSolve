package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.formatExample
import open.geosolve.geosolve.view.formatExpression
import open.geosolve.geosolve.view.formatFormula
import open.geosolve.geosolve.view.formatVerbal
import open.geosolve.geosolve.view.views.recyclers.items.RuleItem
import java.io.Serializable

abstract class Rule : Serializable {
    abstract val verbalID: Int
    abstract val expressionID: Int

    abstract val verbalOrder: List<SolveGraph>
    abstract val expressionOrder: List<SolveGraph>

    abstract val ruleItems: List<RuleItem>

    open val exampleID: Int = 0
    open val exampleOrder: List<SolveGraph> = emptyList()

    val verbal: CharSequence
        get() = formatVerbal(this)
    val formula: CharSequence
        get() = formatFormula(this)
    val expression: CharSequence
        get() = formatExpression(this)

    val stringUpdater: () -> CharSequence = { formatExample(this) }
}