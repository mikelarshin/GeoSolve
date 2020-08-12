package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object RightTriangleRules {
    class pythagorean_theorem(hypotenuse: Line, oneLeg: Line, twoLeg: Line) : Rule() {
        override val verbalID = R.string.verbal_rightTriangle_pythagorean_theorem
        override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem_3

        override val order_for_verbal = listOf(hypotenuse, oneLeg, twoLeg)
        override val order_for_expression = listOf(hypotenuse, oneLeg, twoLeg)

        override val ruleTitle = R.string.ruleTitle_rightTriangle_pythagorean_theorem_3
        override val ruleText = R.string.ruleText_rightTriangle_pythagorean_theorem_3
    }

    class angle_30_degrees(angle30Degrees: Angle, legOpposite30Angle: Line, hypotenuse: Line) : Rule() {
        override val verbalID = R.string.verbal_rightTriangle_angle_30_degrees
        override val expressionID = R.string.expression_rightTriangle_angle_30_degrees

        override val order_for_verbal: List<SolveGraph> = listOf(legOpposite30Angle, angle30Degrees, hypotenuse)
        override val order_for_expression = listOf(legOpposite30Angle, hypotenuse)

        override val ruleTitle = R.string.ruleTitle_rightTriangle_angle_30_degrees
        override val ruleText = R.string.ruleText_rightTriangle_angle_30_degrees
    }

    class unknown_leg_known_leg_and_hypot(unknownLeg: Line, hypotenuse: Line, knownLeg: Line) : Rule() {
        override val verbalID = R.string.verbal_rightTriangle_unknown_leg_known_leg_and_hypot
        override val expressionID = R.string.expression_rightTriangle_unknown_leg_known_leg_and_hypot

        override val order_for_verbal = listOf(unknownLeg, hypotenuse, knownLeg)
        override val order_for_expression = listOf(unknownLeg, hypotenuse, knownLeg)

        override val ruleTitle = R.string.ruleTitle_rightTriangle_unknown_leg_known_leg_and_hypot
        override val ruleText = R.string.ruleText_rightTriangle_unknown_leg_known_leg_and_hypot
    }
}
