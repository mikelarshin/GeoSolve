package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.views.recyclers.StepSolve

object RightTriangleRules {
    object pythagorean_theorem : Article() {
        class MyStep(hypotenuse: Line, oneLeg: Line, twoLeg: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rightTriangle_pythagorean_theorem
            override val expressionID = R.string.expression_rightTriangle_pythagorean_theorem_3

            override val verbalOrder = listOf(hypotenuse, oneLeg, twoLeg)
            override val expressionOrder = listOf(hypotenuse, oneLeg, twoLeg)

            override val article = pythagorean_theorem // TODO(переделай эту жижу)
        }
    }

    object angle_30_degrees : Article() {
        class MyStep(angle30Degrees: Angle, legOpposite30Angle: Line, hypotenuse: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rightTriangle_angle_30_degrees
            override val expressionID = R.string.expression_rightTriangle_angle_30_degrees

            override val verbalOrder: List<SolveGraph> = listOf(legOpposite30Angle, angle30Degrees, hypotenuse)
            override val expressionOrder = listOf(legOpposite30Angle, hypotenuse)

            override val article = angle_30_degrees
        }
    }

    object unknown_leg_known_leg_and_hypot : Article() {
        class MyStep(unknownLeg: Line, hypotenuse: Line, knownLeg: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rightTriangle_unknown_leg_known_leg_and_hypot
            override val expressionID = R.string.expression_rightTriangle_unknown_leg_known_leg_and_hypot

            override val verbalOrder = listOf(unknownLeg, hypotenuse, knownLeg)
            override val expressionOrder = listOf(unknownLeg, hypotenuse, knownLeg)

            override val article = unknown_leg_known_leg_and_hypot
        }
    }
}
