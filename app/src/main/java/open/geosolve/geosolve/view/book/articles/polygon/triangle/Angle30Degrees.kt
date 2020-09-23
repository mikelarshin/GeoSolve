package open.geosolve.geosolve.view.book.articles.polygon.triangle

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.article.items.ArticleItem
import open.geosolve.geosolve.view.article.items.EndItem
import open.geosolve.geosolve.view.article.items.SubTitleItem
import open.geosolve.geosolve.view.article.items.TitleItem
import open.geosolve.geosolve.view.book.articles.Article
import open.geosolve.geosolve.view.solve.StepSolve

object Angle30Degrees : Article() {

    class Step(angle30Degrees: Angle, legOpposite30Angle: Line, hypotenuse: Line) : StepSolve(Angle30Degrees) {
        override val verbalID = R.string.verbal_rightTriangle_angle_30_degrees
        override val expressionID = R.string.expression_rightTriangle_angle_30_degrees

        override val verbalOrder: List<SolveGraph> = listOf(legOpposite30Angle, angle30Degrees, hypotenuse)
        override val expressionOrder = listOf(legOpposite30Angle, hypotenuse)
    }

    override val articleItems: List<ArticleItem> = listOf(
        TitleItem(R.string.ruleTitle_rightTriangle_angle_30_degrees),
        SubTitleItem(R.string.in_progress),
        EndItem()
    )
}