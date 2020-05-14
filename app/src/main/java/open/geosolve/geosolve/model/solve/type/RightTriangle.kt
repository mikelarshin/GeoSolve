package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatSolve
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.sqrt

object RightTriangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mAngles.filter { it.getValue() == 90f }.size == 1
    }

    override fun setGraphs(figure: Figure) {
        val rightAngle = figure.mAngles.filter { it.getValue() == 90f }.first()
        if (rightAngle.startNode.startLine != rightAngle.finalNode.finalLine)
            throw Exception("Wrong Triangle")

        val legs: List<Line> =
            listOf(rightAngle.angleNode.startLine!!, rightAngle.angleNode.finalLine!!)
        val hypotenuse = rightAngle.startNode.startLine!!
        val noRightAngles: List<Angle> = figure.mAngles.filter { it != rightAngle }

        legs2KnownHypotUnknownRule(legs, hypotenuse)
        degrees30Rule(noRightAngles, legs, hypotenuse)
        findLegKnownLegAndHypotRule(hypotenuse, legs)
    }

    override fun setSubType(figure: Figure) {}

    private fun legs2KnownHypotUnknownRule(legs: List<Line>, hypotenuse: Line) {
        for (i in 0..1) {
            legs[i].onKnownFunList.add { oneLeg ->
                val twoLeg = legs[(i + 1) % 2]
                if (twoLeg.getValue() != null && hypotenuse.getValue() == null) {
                    val valueGetter: (Float?) -> Float? = { hypot(oneLeg.getValue()!!, twoLeg.getValue()!!) }

                    hypotenuse.setDependentValueGraph(
                        valueGetter,
                        listOf(oneLeg, twoLeg),
                        formatSolve(
                            R.string.verbal_triangle_right_pythagorean_theorem_3,
                            R.string.expression_triangle_right_pythagorean_theorem_3,
                            hypotenuse,
                            oneLeg,
                            twoLeg
                        )
                    )
                }
            }
        }
    }

    private fun degrees30Rule(noRightAngles: List<Angle>, legs: List<Line>, hypotenuse: Line) {
        for (i in 0..1)
            noRightAngles[i].onKnownFunList.add { thisElement ->
                if (thisElement.getValue() == 30f && hypotenuse.getValue() != null) {
                    val otherAngle = noRightAngles[(i + 1) % 2]

                    val unknownLeg = legs.first {
                        it == otherAngle.angleNode.finalLine ||
                                it == otherAngle.angleNode.startLine
                    }
                    if (unknownLeg.getValue() == null) {
                        val valueGetter: (Float?) -> Float? = { hypotenuse.getValue()!! / 2 }

                        unknownLeg.setDependentValueGraph(
                            valueGetter,
                            listOf(thisElement, hypotenuse),
                            formatSolve(
                                R.string.verbal_triangle_right_30_degrees_2,
                                R.string.expression_triangle_right_30_degrees_2,
                                unknownLeg,
                                hypotenuse
                            )
                        )
                    }
                }
            }
    }

    private fun findLegKnownLegAndHypotRule(hypotenuse: Line, legs: List<Line>){
        for (i in 0..1){
            legs[i].onKnownFunList.add {thisLeg ->
                if (hypotenuse.getValue() != null) {
                    val unknownLeg = legs[(i + 1) % 2]

                    if (unknownLeg.getValue() == null) {
                        val valueGetter: (Float?) -> Float? = {
                            sqrt(hypotenuse.getValue()!!.pow(2) -
                                    thisLeg.getValue()!!.pow(2)) }

                        unknownLeg.setDependentValueGraph(
                            valueGetter,
                            listOf(thisLeg, hypotenuse),
                            formatSolve(
                                R.string.verbal_triangle_right_find_leg_known_leg_hypot_3,
                                R.string.expression_triangle_right_find_leg_known_leg_hypot_3,
                                unknownLeg,
                                hypotenuse,
                                thisLeg
                            )
                        )
                    }
                }
            }
        }

//        hypotenuse.onKnownFunList.add {
//            for (i in 0..1)
//                if (legs[i].getValue() != null && legs[(i + 1) % 2].getValue() != null)
//        }
    }
}