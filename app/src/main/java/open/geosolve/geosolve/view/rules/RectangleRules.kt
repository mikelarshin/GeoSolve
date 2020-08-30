package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Line
import open.geosolve.geosolve.view.views.recyclers.items.RuleItem
import open.geosolve.geosolve.view.views.recyclers.items.TextItem
import open.geosolve.geosolve.view.views.recyclers.items.TitleItem

object RectangleRules {
    class parallel_line(knownLine: Line, parallelLine: Line) : Rule() {
        override val verbalID = R.string.verbal_rectangle_parallel_line
        override val expressionID = R.string.expression_rectangle_parallel_line

        override val verbalOrder = listOf(parallelLine, knownLine)
        override val expressionOrder = listOf(parallelLine, knownLine)

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_rectangle_parallel_line),
            TextItem(R.string.ruleText_rectangle_parallel_line))
    }

    class right_angles(unknownAngle: Angle) : Rule() {
        override val verbalID = R.string.verbal_rectangle_right_angles
        override val expressionID = R.string.expression_rectangle_right_angles

        override val verbalOrder = listOf(unknownAngle)
        override val expressionOrder = listOf(unknownAngle)

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_rectangle_right_angles),
            TextItem(R.string.ruleText_rectangle_right_angles))
    }
}