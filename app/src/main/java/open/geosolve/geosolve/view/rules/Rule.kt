package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.DesignUtil
import open.geosolve.geosolve.view.DesignUtil.formatFormula
import open.geosolve.geosolve.view.DesignUtil.formatVerbal
import java.io.Serializable

abstract class Rule : Serializable {
    abstract val verbalID: Int
    abstract val expressionID: Int

    abstract val ruleTitle: Int
    abstract val ruleText: Int

    abstract val order_for_verbal: List<SolveGraph>
    abstract val order_for_expression: List<SolveGraph>

    val verbal: CharSequence
        get() = formatVerbal(this)
    val formula: CharSequence
        get() = formatFormula(this)
    val expression: CharSequence
        get() = DesignUtil.formatExpression(this)
}