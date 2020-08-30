package open.geosolve.geosolve.view.rules

import android.text.SpannableStringBuilder
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.AllAngles
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.find
import open.geosolve.geosolve.model.canvas.math.MathUtil
import open.geosolve.geosolve.model.canvas.tools.MarkTool
import open.geosolve.geosolve.model.canvas.tools.MoveTool
import open.geosolve.geosolve.view.*
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.items.*
import kotlin.math.absoluteValue

object TriangleRules {
    class know_2_unknown_1_angle(unknown_angle: Angle, known_angle_1: Angle, known_angle_2: Angle) : Rule() {
        override val verbalID = R.string.verbal_triangle_know_2_unknown_1_angle
        override val expressionID = R.string.expression_triangle_know_2_unknown_1_angle

        override val verbalOrder = listOf(unknown_angle, known_angle_1, known_angle_2)
        override val expressionOrder = listOf(unknown_angle, known_angle_1, known_angle_2)

        private val stringUpdaterCheck = { // TODO(rewrite this shit)
            setSize(
                formatExample(
                    formatAllDigital(
                        R.string.ruleExample_triangle_know_2_unknown_1_angle_check,
                        R.style.AnswerText
                    ),
                    AllAngles.toList()
                ),
                R.dimen.BIG_TEXT_SIZE
            )
        }

        private val stringUpdaterExperiment = {
            val angles = (linkedSetOf(find!!) + AllAngles).toList()

            val sb = SpannableStringBuilder()

            sb.append(setSize(
                formatSolveText(
                    R.string.ruleExample_triangle_know_2_unknown_1_angle_experiment,
                    angles
                ) { (it as Angle).toSmallString() },
                R.dimen.BIG_TEXT_SIZE
            ))
            sb.append("\n")
            sb.append(setSize(
                formatSolveText(
                    R.string.ruleExample_triangle_know_2_unknown_1_angle_experiment,
                    angles
                ) { formatValueString(it, 0) },
                R.dimen.BIG_TEXT_SIZE
            ))
        }

        private val exampleTriangleCheck: () -> CanvasData = {
            val dataCanvas = CanvasData()
            makeTriangleOne()

            val angleList: List<Angle> = AllAngles.toList()
            angleList[0].setDependentValueDraw { MathUtil.getDegree(angleList[0].startNode, angleList[0].angleNode, angleList[0].finalNode).absoluteValue }
            angleList[1].setDependentValueDraw { MathUtil.getDegree(angleList[1].startNode, angleList[1].angleNode, angleList[1].finalNode).absoluteValue }
            angleList[2].setDependentValueDraw { 180f - (angleList[0].getValue()!! + angleList[1].getValue()!!) }

            dataCanvas
        }

        private val exampleTriangleExperiment: () -> CanvasData = {
            val dataCanvas = CanvasData()
            makeTriangleTwo()

            val angleList: List<Angle> = AllAngles.toList()
            angleList[0].setDependentValueDraw { MathUtil.getDegree(angleList[0].startNode, angleList[0].angleNode, angleList[0].finalNode).absoluteValue }
            angleList[1].setDependentValueDraw { MathUtil.getDegree(angleList[1].startNode, angleList[1].angleNode, angleList[1].finalNode).absoluteValue }
            angleList[2].setDependentValueDraw { 180f - (angleList[0].getValue()!! + angleList[1].getValue()!!) }

            find = angleList.find { it.getValue() == 90f }

            dataCanvas
        }

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_triangle_know_2_unknown_1_angle),
//            SubTitleItem(R.string.ruleSubTitle_wording),
//            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_wording), TODO(make smth with that)
            SubTitleItem(R.string.ruleSubTitle_check),
            ExampleFigureItem(exampleTriangleCheck, stringUpdaterCheck, MoveTool),
//            SubTitleItem(R.string.ruleSubTitle_evidence),
//            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_evidence), TODO(make smth with that)
            SubTitleItem(R.string.ruleSubTitle_use),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_use),
            FormulaItem(R.string.ruleFormula_triangle_know_2_unknown_1_angle),
            SubTitleItem(R.string.ruleSubTitle_experiment),
            ExampleFigureItem(exampleTriangleExperiment, stringUpdaterExperiment, MarkTool),
            EndItem()
        )
    }
}