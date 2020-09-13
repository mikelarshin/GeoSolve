package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.view.book.articles.RightTriangleRules
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.sqrt

object RightTriangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mAngles.filter { it.getValue() == 90f }.size == 1
    }

    override fun setGraphs(figure: Figure) {
        val rightAngle = figure.mAngles.first { it.getValue() == 90f }

        val legs: List<Line> = rightAngle.lines
        val hypotenuse = figure.mLines.first { !legs.contains(it) }
        val noRightAngles: List<Angle> = figure.mAngles.filter { it != rightAngle }

        knownLegsUnknownHypotenuse(legs, hypotenuse)
        degrees30Rule(noRightAngles, hypotenuse)
        findLegKnownLegAndHypotRule(hypotenuse, legs)
    }

    private fun knownLegsUnknownHypotenuse(legs: List<Line>, hypotenuse: Line) { // известны катеты, ищем гипотенузу
        for (i in 0..1) {
            legs[i].onKnownFunctions.add { oneLeg ->
                val twoLeg = legs[(i + 1) % 2]
                oneLeg as Line

                if (twoLeg.getValue() != null && hypotenuse.getValue() == null) {
                    val valueGetter: (Float?) -> Float? = { hypot(oneLeg.getValue()!!, twoLeg.getValue()!!) }

                    hypotenuse.setDependentValueGraph(
                        valueGetter,
                        listOf(oneLeg, twoLeg),
                        RightTriangleRules.pythagorean_theorem.UnknownHypotStep(hypotenuse, oneLeg, twoLeg)
                    )
                }
            }
        }
    }

    private fun degrees30Rule(noRightAngles: List<Angle>, hypotenuse: Line) {
        noRightAngles.forEach { angle ->
            angle.onKnownFunctions.add { degree30Angle ->
                degree30Angle as Angle
                if (degree30Angle.getValue() == 30f && // угол в 30 градусов
                    hypotenuse.getValue() != null) { // есть значение гипотенузы

                    val not30AngleLegs = noRightAngles.first { it != degree30Angle }.lines // линии угла не в 30
                    val legOpposite30Angle = not30AngleLegs.first { it != hypotenuse }

                    val valueGetter: (Float?) -> Float? = { hypotenuse.getValue()!! / 2 }

                    legOpposite30Angle.setDependentValueGraph(
                        valueGetter,
                        listOf(degree30Angle, hypotenuse),
                        RightTriangleRules.angle_30_degrees.MyStep(degree30Angle, legOpposite30Angle, hypotenuse)
                    )
                }
            }
        }
    }

    private fun findLegKnownLegAndHypotRule(hypotenuse: Line, legs: List<Line>) {
        for (i in 0..1) {
            legs[i].onKnownFunctions.add { knownLeg ->
                knownLeg as Line
                if (hypotenuse.getValue() != null) {
                    val unknownLeg = legs[(i + 1) % 2]

                    if (unknownLeg.getValue() == null) {
                        val valueGetter: (Float?) -> Float? = {
                            sqrt(
                                hypotenuse.getValue()!!.pow(2) -
                                        knownLeg.getValue()!!.pow(2)
                            )
                        }

                        unknownLeg.setDependentValueGraph(
                            valueGetter,
                            listOf(knownLeg, hypotenuse),
                            RightTriangleRules.pythagorean_theorem.UnknownLegStep(unknownLeg, hypotenuse, knownLeg)
                        )
                    }
                }
            }
        }
    }
}