package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.AllAngles
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.math.MathUtil
import open.geosolve.geosolve.view.formatValueString
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.items.*
import kotlin.math.absoluteValue

object TriangleRules {
    class know_2_unknown_1_angle(unknown_angle: Angle, known_angle_1: Angle, known_angle_2: Angle) : Rule() {
        override val verbalID = R.string.verbal_triangle_know_2_unknown_1_angle
        override val expressionID = R.string.expression_triangle_know_2_unknown_1_angle

        override val verbalOrder = listOf(unknown_angle, known_angle_1, known_angle_2)
        override val expressionOrder = listOf(unknown_angle, known_angle_1, known_angle_2)

        override val exampleID: Int = R.string.ruleExample_triangle_know_2_unknown_1_angle
        override val exampleOrder: List<SolveGraph> get() = AllAngles.toList()

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_triangle_know_2_unknown_1_angle),
            SubTitleItem(R.string.ruleSubTitle_wording),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_wording),
            SubTitleItem(R.string.ruleSubTitle_evidence),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_evidence),
            SubTitleItem(R.string.ruleSubTitle_experiment),
            ExampleFigureItem(exampleTriangle, stringUpdater),
            SubTitleItem(R.string.ruleSubTitle_conclusion),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_conclusion),
            FormulaItem(R.string.ruleFormula_triangle_know_2_unknown_1_angle),
            EndItem()
        )

        private val exampleTriangle: CanvasData
            get() {
                val dataCanvas = CanvasData()
                makeTriangle()

                val angleList: List<Angle> = AllAngles.toList()
                angleList[0].setDependentValueDraw { MathUtil.getDegree(angleList[0].startNode, angleList[0].angleNode, angleList[0].finalNode).absoluteValue }
                angleList[1].setDependentValueDraw { MathUtil.getDegree(angleList[1].startNode, angleList[1].angleNode, angleList[1].finalNode).absoluteValue }
                angleList[2].setDependentValueDraw { 180f - (angleList[0].getValue()!! + angleList[1].getValue()!!) }

                return dataCanvas
            }
    }
}