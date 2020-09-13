package open.geosolve.geosolve.view.book.articles

import android.text.SpannableStringBuilder
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.controllers.AllAngles
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.controllers.find
import open.geosolve.geosolve.model.canvas.tools.MarkTool
import open.geosolve.geosolve.model.canvas.tools.MoveTool
import open.geosolve.geosolve.view.solve.StepSolve
import open.geosolve.geosolve.view.article.items.*
import open.geosolve.geosolve.view.helpers.*

object TriangleRules {
    object know_2_unknown_1_angle : Article() {
        class MyStep(unknown_angle: Angle, known_angle_1: Angle, known_angle_2: Angle) : StepSolve() {
            override val verbalID = R.string.verbal_triangle_know_2_unknown_1_angle
            override val expressionID = R.string.expression_triangle_know_2_unknown_1_angle

            override val verbalOrder = listOf(unknown_angle, known_angle_1, known_angle_2)
            override val expressionOrder = listOf(unknown_angle, known_angle_1, known_angle_2)

            override val article = know_2_unknown_1_angle
        }

        private val stringUpdaterCheck = {
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

        private val stringUpdaterExperiment = { // TODO(rewrite this shit!!!)
            val sb = SpannableStringBuilder()

            if (find!! !is Angle) { // TODO(rewrite this shit!!!)
                sb
            } else {

                val angles = (linkedSetOf(find!!) + AllAngles).toList()

                sb.apply {
                    append(
                        setSize(
                            formatSolveText(
                                R.string.ruleExample_triangle_know_2_unknown_1_angle_experiment,
                                angles
                            ) { (it as Angle).toSmallString() },
                            R.dimen.BIG_TEXT_SIZE
                        )
                    )
                    append("\n")
                    append(
                        setSize(
                            formatSolveText(
                                R.string.ruleExample_triangle_know_2_unknown_1_angle_experiment,
                                angles
                            ) { formatValueString(it, 0) },
                            R.dimen.BIG_TEXT_SIZE
                        )
                    )
                }
            }
        }

        private val exampleTriangleCheck: () -> CanvasData = {
            CanvasData().apply {
                makeTriangleOne()
                makeRealAngles()
            }
        }

        private val exampleTriangleExperiment: () -> CanvasData = {
            CanvasData().apply {
                makeTriangleTwo()
                makeRealAngles()

                find = AllAngles.find { it.getValue() == 90f }
            }
        }

        override val articleItems: List<ArticleItem> = listOf(
            TitleItem(R.string.ruleTitle_triangle_know_2_unknown_1_angle),
            SubTitleItem(R.string.ruleSubTitle_check),
            ExampleFigureItem(exampleTriangleCheck, stringUpdaterCheck, MoveTool),
            SubTitleItem(R.string.ruleSubTitle_use),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_use),
            FormulaItem(R.string.ruleFormula_triangle_know_2_unknown_1_angle),
            SubTitleItem(R.string.ruleSubTitle_experiment),
            ExampleFigureItem(exampleTriangleExperiment, stringUpdaterExperiment, MarkTool),
            EndItem()
        )
    }
}