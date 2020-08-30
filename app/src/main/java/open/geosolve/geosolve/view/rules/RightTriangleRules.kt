package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.views.recyclers.items.RuleItem
import open.geosolve.geosolve.view.views.recyclers.items.TextItem
import open.geosolve.geosolve.view.views.recyclers.items.TitleItem

object RightTriangleRules {
    class pythagorean_theorem(hypotenuse: Line, oneLeg: Line, twoLeg: Line) : Rule() {
        override val verbalID = R.string.verbal_rightTriangle_pythagorean_theorem
        override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem_3

        override val verbalOrder = listOf(hypotenuse, oneLeg, twoLeg)
        override val expressionOrder = listOf(hypotenuse, oneLeg, twoLeg)

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_rightTriangle_pythagorean_theorem_3),
            TextItem(R.string.ruleText_rightTriangle_pythagorean_theorem_3))
    }

    class angle_30_degrees(angle30Degrees: Angle, legOpposite30Angle: Line, hypotenuse: Line) : Rule() {
        override val verbalID = R.string.verbal_rightTriangle_angle_30_degrees
        override val expressionID = R.string.expression_rightTriangle_angle_30_degrees

        override val verbalOrder: List<SolveGraph> = listOf(legOpposite30Angle, angle30Degrees, hypotenuse)
        override val expressionOrder = listOf(legOpposite30Angle, hypotenuse)

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_rightTriangle_angle_30_degrees),
            TextItem(R.string.ruleText_rightTriangle_angle_30_degrees))
    }

    class unknown_leg_known_leg_and_hypot(unknownLeg: Line, hypotenuse: Line, knownLeg: Line) : Rule() {
        override val verbalID = R.string.verbal_rightTriangle_unknown_leg_known_leg_and_hypot
        override val expressionID = R.string.expression_rightTriangle_unknown_leg_known_leg_and_hypot

        override val verbalOrder = listOf(unknownLeg, hypotenuse, knownLeg)
        override val expressionOrder = listOf(unknownLeg, hypotenuse, knownLeg)

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_rightTriangle_unknown_leg_known_leg_and_hypot),
            TextItem(R.string.ruleText_rightTriangle_unknown_leg_known_leg_and_hypot))
    }
}
