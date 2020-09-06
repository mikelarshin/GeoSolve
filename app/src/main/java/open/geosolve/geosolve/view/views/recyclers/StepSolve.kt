package open.geosolve.geosolve.view.views.recyclers

import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.formatExpression
import open.geosolve.geosolve.view.formatFormula
import open.geosolve.geosolve.view.formatVerbal
import open.geosolve.geosolve.view.rules.Article

abstract class StepSolve {
    abstract val verbalID: Int
    abstract val expressionID: Int

    abstract val verbalOrder: List<SolveGraph>
    abstract val expressionOrder: List<SolveGraph>

    val verbal: CharSequence
        get() = formatVerbal(this)
    val formula: CharSequence
        get() = formatFormula(this)
    val expression: CharSequence
        get() = formatExpression(this)

    abstract val article: Article
}
