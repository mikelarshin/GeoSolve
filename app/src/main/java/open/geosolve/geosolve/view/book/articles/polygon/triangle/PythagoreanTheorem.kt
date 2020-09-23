package open.geosolve.geosolve.view.book.articles.polygon.triangle

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.view.article.items.ArticleItem
import open.geosolve.geosolve.view.article.items.EndItem
import open.geosolve.geosolve.view.article.items.SubTitleItem
import open.geosolve.geosolve.view.article.items.TitleItem
import open.geosolve.geosolve.view.book.articles.Article
import open.geosolve.geosolve.view.solve.StepSolve

object PythagoreanTheorem : Article() {

    class UnknownHypotStep(hypotenuse: Line, oneLeg: Line, twoLeg: Line) : StepSolve(PythagoreanTheorem) {
        override val verbalID = R.string.verbal_rightTriangle_pythagorean_theorem
        override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem_hypot

        override val verbalOrder = listOf(hypotenuse, oneLeg, twoLeg)
        override val expressionOrder = listOf(hypotenuse, oneLeg, twoLeg)
    }

    class UnknownLegStep(unknownLeg: Line, hypotenuse: Line, knownLeg: Line) : StepSolve(PythagoreanTheorem) {
        override val verbalID = R.string.verbal_rightTriangle_unknown_leg_known_leg_and_hypot
        override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem_leg

        override val verbalOrder = listOf(unknownLeg, hypotenuse, knownLeg)
        override val expressionOrder = listOf(unknownLeg, hypotenuse, knownLeg)
    }

    override val articleItems: List<ArticleItem> = listOf(
        TitleItem(R.string.ruleTitle_rightTriangle_pythagorean_theorem),
        SubTitleItem(R.string.in_progress),
        EndItem()
    )
}