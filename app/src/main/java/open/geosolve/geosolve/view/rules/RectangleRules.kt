package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.view.views.recyclers.StepSolve

object RectangleRules {
    object parallel_line : Article() {
        class MyStep(knownLine: Line, parallelLine: Line) : StepSolve() {
            override val verbalID = R.string.verbal_rectangle_parallel_line
            override val expressionID = R.string.expression_rectangle_parallel_line

            override val verbalOrder = listOf(parallelLine, knownLine)
            override val expressionOrder = listOf(parallelLine, knownLine)

            override val article = parallel_line
        }
    }

    object right_angles : Article() {
        class MyStep(unknownAngle: Angle) : StepSolve() {
            override val verbalID = R.string.verbal_rectangle_right_angles
            override val expressionID = R.string.expression_rectangle_right_angles

            override val verbalOrder = listOf(unknownAngle)
            override val expressionOrder = listOf(unknownAngle)

            override val article = right_angles
        }
    }
}