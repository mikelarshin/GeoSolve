package open.geosolve.geosolve.view.book.articles.polygon.quadrangle

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.view.article.items.ArticleItem
import open.geosolve.geosolve.view.article.items.EndItem
import open.geosolve.geosolve.view.article.items.SubTitleItem
import open.geosolve.geosolve.view.article.items.TitleItem
import open.geosolve.geosolve.view.book.articles.Article
import open.geosolve.geosolve.view.solve.StepSolve

object ParallelLines : Article() {
    class Step(knownLine: Line, parallelLine: Line) : StepSolve(ParallelLines) {
        override val verbalID = R.string.verbal_rectangle_parallel_line
        override val expressionID = R.string.expression_rectangle_parallel_line

        override val verbalOrder = listOf(parallelLine, knownLine)
        override val expressionOrder = listOf(parallelLine, knownLine)
    }

    override val articleItems: List<ArticleItem> = listOf(
        TitleItem(R.string.ruleTitle_rectangle_parallel_line),
        SubTitleItem(R.string.in_progress),
        EndItem()
    )
}