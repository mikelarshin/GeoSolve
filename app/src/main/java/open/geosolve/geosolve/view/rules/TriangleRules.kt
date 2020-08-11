package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle

object TriangleRules {
    class know_2_unknown_1_angle(unknown_angle: Angle, known_angle_1: Angle, known_angle_2: Angle) : Rule() {
        override val verbalID = R.string.verbal_triangle_know_2_unknown_1_angle
        override val expressionID = R.string.expression_triangle_know_2_unknown_1_angle

        override val order_for_verbal = listOf(unknown_angle, known_angle_1, known_angle_2)
        override val order_for_expression = listOf(unknown_angle, known_angle_1, known_angle_2)
    }
}