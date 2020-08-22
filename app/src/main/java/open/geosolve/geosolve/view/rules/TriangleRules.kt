package open.geosolve.geosolve.view.rules

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.AllAngles
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.math.MathUtil
import open.geosolve.geosolve.model.tools.AddTool
import open.geosolve.geosolve.model.tools.BaseTool
import open.geosolve.geosolve.view.views.canvas.CanvasData
import open.geosolve.geosolve.view.views.recyclers.items.*
import kotlin.math.absoluteValue

object TriangleRules {
    class know_2_unknown_1_angle(unknown_angle: Angle, known_angle_1: Angle, known_angle_2: Angle) : Rule() {
        override val verbalID = R.string.verbal_triangle_know_2_unknown_1_angle
        override val expressionID = R.string.expression_triangle_know_2_unknown_1_angle

        override val order_for_verbal = listOf(unknown_angle, known_angle_1, known_angle_2)
        override val order_for_expression = listOf(unknown_angle, known_angle_1, known_angle_2)

        override val ruleItems: List<RuleItem> = listOf(
            TitleItem(R.string.ruleTitle_triangle_know_2_unknown_1_angle),
            SubTitleItem(R.string.ruleSubTitle_wording),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_wording),
            SubTitleItem(R.string.ruleSubTitle_evidence),
            TextItem(R.string.ruleText_triangle_know_2_unknown_1_angle_evidence),
            ExempleFigureItem(exempleTriangle),
            ExempleFigureItem(exempleTriangle2),
            ExempleFigureItem(exempleCircle),
            ExempleFigureItem(exempleReactangle)
        )

        private val exempleTriangle: CanvasData
            get() {
                val dataCanvas = CanvasData()

                AddTool.cycleTouch(-10f, -10f)
                AddTool.cycleTouch(10f, -10f)
                AddTool.cycleTouch(-10f, 10f)
                AddTool.cycleTouch(-10f, -10f)

                val angleList: List<Angle> = AllAngles.toList()
                angleList[0].setDependentValueDraw { MathUtil.getDegree(angleList[0].startNode, angleList[0].angleNode, angleList[0].finalNode).absoluteValue }
                angleList[1].setDependentValueDraw { MathUtil.getDegree(angleList[1].startNode, angleList[1].angleNode, angleList[1].finalNode).absoluteValue }
                angleList[2].setDependentValueDraw { 180f - (angleList[0].getValue()!! + angleList[1].getValue()!!) }

                return dataCanvas
        }

        private val exempleTriangle2: CanvasData
            get() {
                val dataCanvas = CanvasData()

                AddTool.cycleTouch(-10f, -10f)
                AddTool.cycleTouch(10f, -10f)
                AddTool.cycleTouch(10f, 10f)
                AddTool.cycleTouch(-10f, -10f)

                val angleList: List<Angle> = AllAngles.toList()
                angleList[0].setDependentValueDraw { MathUtil.getDegree(angleList[0].startNode, angleList[0].angleNode, angleList[0].finalNode).absoluteValue }
                angleList[1].setDependentValueDraw { MathUtil.getDegree(angleList[1].startNode, angleList[1].angleNode, angleList[1].finalNode).absoluteValue }
                angleList[2].setDependentValueDraw { 180f - (angleList[0].getValue()!! + angleList[1].getValue()!!) }

                return dataCanvas
            }

        private val exempleCircle: CanvasData
            get() {
                val dataCanvas = CanvasData()

                BaseTool.moveQuantity = 5
                AddTool.onTouchMove(0f, 0f)
                AddTool.onTouchMove(10f, 0f)
                AddTool.onTouchUp(10f, 0f)

                return dataCanvas
            }

        private val exempleReactangle: CanvasData
            get() {
                val dataCanvas = CanvasData()

                AddTool.cycleTouch(-10f, -10f)
                AddTool.cycleTouch(10f, -10f)
                AddTool.cycleTouch(10f, 10f)
                AddTool.cycleTouch(-10f, 10f)
                AddTool.cycleTouch(-10f, -10f)

                return dataCanvas
            }
    }
}