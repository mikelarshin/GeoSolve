package open.geosolve.geosolve.view.solve

import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.helpers.formatExpression
import open.geosolve.geosolve.view.helpers.formatFormula
import open.geosolve.geosolve.view.helpers.formatVerbal
import open.geosolve.geosolve.view.book.articles.Article

abstract class StepSolve(val article: Article) {
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
}
