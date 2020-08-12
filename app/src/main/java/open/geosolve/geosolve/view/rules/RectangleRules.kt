package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Line

object RectangleRules {
    class parallel_line(knownLine: Line, parallelLine: Line) : Rule() {
        override val verbalID = R.string.verbal_rectangle_parallel_line
        override val expressionID = R.string.expression_rectangle_parallel_line

        override val order_for_verbal = listOf(parallelLine, knownLine)
        override val order_for_expression = listOf(parallelLine, knownLine)

        override val ruleTitle = R.string.ruleTitle_rectangle_parallel_line
        override val ruleText = R.string.ruleText_rectangle_parallel_line
    }

    class right_angles(unknownAngle: Angle) : Rule() {
        override val verbalID = R.string.verbal_rectangle_right_angles
        override val expressionID = R.string.expression_rectangle_right_angles

        override val order_for_verbal = listOf(unknownAngle)
        override val order_for_expression = listOf(unknownAngle)

        override val ruleTitle = R.string.ruleTitle_rectangle_right_angles
        override val ruleText = R.string.ruleText_rectangle_right_angles
    }
}