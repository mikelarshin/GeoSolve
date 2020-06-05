package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.view.screens.DesignUtil.formatSolve
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.sqrt

object RightTriangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mAngles.filter { it.getValue() == 90f }.size == 1
    }

    override fun setGraphs(figure: Figure) {
        val rightAngle = figure.mAngles.first { it.getValue() == 90f }

        val legs: List<Line> = listOf(rightAngle.startLine, rightAngle.finalLine)
        val hypotenuse = figure.mLines.first { !legs.contains(it) }
        val noRightAngles: List<Angle> = figure.mAngles.filter { it != rightAngle }

        legsKnownHypotenuseUnknown(legs, hypotenuse)
        degrees30Rule(noRightAngles, hypotenuse)
        findLegKnownLegAndHypotRule(hypotenuse, legs)
    }

    override fun setSubType(figure: Figure) {}

    private fun legsKnownHypotenuseUnknown(legs: List<Line>, hypotenuse: Line) { // известны катеты, ищем гипотенузу
        for (i in 0..1) {
            legs[i].onKnownFunList.add { oneLeg ->
                val twoLeg = legs[(i + 1) % 2]
                // TODO(создать функцию которая берёт следующий по списку элемент как здесь)

                // TODO(сднлпть функцию которая проверяет на входные данные и искомое)
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

    private fun degrees30Rule(noRightAngles: List<Angle>, hypotenuse: Line) {
        noRightAngles.forEach { angle ->
            angle.onKnownFunList.add { thisAngle ->
                if (thisAngle.getValue() == 30f && // новый угол в 30 градусов
                    hypotenuse.getValue() != null) { // есть значение гипотенузы

                    val not30AngleLegs = noRightAngles.first { it != thisAngle }.lines // линии угла не в 30
                    val legOpposite30Angle = not30AngleLegs.first { it != hypotenuse }

                    val valueGetter: (Float?) -> Float? = { hypotenuse.getValue()!! / 2 }

                    legOpposite30Angle.setDependentValueGraph(
                        valueGetter,
                        listOf(thisAngle, hypotenuse),
                        formatSolve(
                            R.string.verbal_triangle_right_30_degrees_2,
                            R.string.expression_triangle_right_30_degrees_2,
                            legOpposite30Angle,
                            hypotenuse
                        )
                    )
                }
            }
        }
    }

    private fun findLegKnownLegAndHypotRule(hypotenuse: Line, legs: List<Line>) {
        for (i in 0..1) {
            legs[i].onKnownFunList.add { thisLeg ->
                if (hypotenuse.getValue() != null) {
                    val unknownLeg = legs[(i + 1) % 2]

                    if (unknownLeg.getValue() == null) {
                        val valueGetter: (Float?) -> Float? = {
                            sqrt(
                                hypotenuse.getValue()!!.pow(2) -
                                        thisLeg.getValue()!!.pow(2)
                            )
                        }

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
    }
}