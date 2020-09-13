package open.geosolve.geosolve.view.book.articles

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.view.solve.StepSolve
import open.geosolve.geosolve.view.article.items.ArticleItem
import open.geosolve.geosolve.view.article.items.EndItem
import open.geosolve.geosolve.view.article.items.SubTitleItem
import open.geosolve.geosolve.view.article.items.TitleItem

object RectangleRules {
    object parallel_line : Article() {
        class MyStep(knownLine: Line, parallelLine: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rectangle_parallel_line
            override val expressionID = R.string.expression_rectangle_parallel_line

            override val verbalOrder = listOf(parallelLine, knownLine)
            override val expressionOrder = listOf(parallelLine, knownLine)

            override val article = parallel_line
        }

        override val articleItems: List<ArticleItem> = listOf(
            TitleItem(R.string.ruleTitle_rectangle_parallel_line),
            SubTitleItem(R.string.in_progress),
            EndItem()
        )
    }

    object right_angles : Article() {
        class MyStep(unknownAngle: Angle) : StepSolve() {
            override val verbalID = R.string.verbal_rectangle_right_angles
            override val expressionID = R.string.expression_rectangle_right_angles

            override val verbalOrder = listOf(unknownAngle)
            override val expressionOrder = listOf(unknownAngle)

            override val article = right_angles
        }

        override val articleItems: List<ArticleItem> = listOf(
            TitleItem(R.string.ruleTitle_rectangle_right_angles),
            SubTitleItem(R.string.in_progress),
            EndItem()
        )
    }
}