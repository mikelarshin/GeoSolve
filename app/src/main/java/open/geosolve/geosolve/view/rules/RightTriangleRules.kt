package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.model.canvas.tools.MarkTool
import open.geosolve.geosolve.model.canvas.tools.MoveTool
import open.geosolve.geosolve.view.views.recyclers.StepSolve
import open.geosolve.geosolve.view.views.recyclers.items.*

object RightTriangleRules {
    object pythagorean_theorem : Article() {
        class UnknownHypotStep(hypotenuse: Line, oneLeg: Line, twoLeg: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rightTriangle_pythagorean_theorem
            override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem

            override val verbalOrder = listOf(hypotenuse, oneLeg, twoLeg)
            override val expressionOrder = listOf(hypotenuse, oneLeg, twoLeg)

            override val article = pythagorean_theorem // TODO(переделай эту жижу)
        }

        class UnknownLegStep(unknownLeg: Line, hypotenuse: Line, knownLeg: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rightTriangle_unknown_leg_known_leg_and_hypot
            override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem

            override val verbalOrder = listOf(unknownLeg, hypotenuse, knownLeg)
            override val expressionOrder = listOf(unknownLeg, hypotenuse, knownLeg)

            override val article = pythagorean_theorem
        }

        override val articleItems: List<ArticleItem> = listOf(
            TitleItem(R.string.ruleTitle_rightTriangle_pythagorean_theorem),
            SubTitleItem(R.string.in_progress),
            EndItem()
        )
    }

    object angle_30_degrees : Article() {
        class MyStep(angle30Degrees: Angle, legOpposite30Angle: Line, hypotenuse: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rightTriangle_angle_30_degrees
            override val expressionID = R.string.expression_rightTriangle_angle_30_degrees

            override val verbalOrder: List<SolveGraph> = listOf(legOpposite30Angle, angle30Degrees, hypotenuse)
            override val expressionOrder = listOf(legOpposite30Angle, hypotenuse)

            override val article = angle_30_degrees
        }

        override val articleItems: List<ArticleItem> = listOf(
            TitleItem(R.string.ruleTitle_rightTriangle_angle_30_degrees),
            SubTitleItem(R.string.in_progress),
            EndItem()
        )
    }
}
