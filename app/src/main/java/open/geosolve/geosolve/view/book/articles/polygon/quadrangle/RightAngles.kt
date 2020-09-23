package open.geosolve.geosolve.view.book.articles.polygon.quadrangle

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.view.article.items.ArticleItem
import open.geosolve.geosolve.view.article.items.EndItem
import open.geosolve.geosolve.view.article.items.SubTitleItem
import open.geosolve.geosolve.view.article.items.TitleItem
import open.geosolve.geosolve.view.book.articles.Article
import open.geosolve.geosolve.view.solve.StepSolve

object RightAngles : Article() {
    class Step(unknownAngle: Angle) : StepSolve(RightAngles) {
        override val verbalID = R.string.verbal_rectangle_right_angles
        override val expressionID = R.string.expression_rectangle_right_angles

        override val verbalOrder = listOf(unknownAngle)
        override val expressionOrder = listOf(unknownAngle)
    }

    override val articleItems: List<ArticleItem> = listOf(
        TitleItem(R.string.ruleTitle_rectangle_right_angles),
        SubTitleItem(R.string.in_progress),
        EndItem()
    )
}