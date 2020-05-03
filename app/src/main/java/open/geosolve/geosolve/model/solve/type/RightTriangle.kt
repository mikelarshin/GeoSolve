package open.geosolve.geosolve.model.solve.type

import android.annotation.SuppressLint
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatSolve
import kotlin.math.pow
import kotlin.math.sqrt

object RightTriangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mAngles.filter { it.getValue() == 90f }.size == 1
    }

    override fun setGraphs(figure: Figure) {
        val rightAngle = figure.mAngles.filter { it.getValue() == 90f }[0]
        if (rightAngle.startNode.startLine != rightAngle.finalNode.finalLine)
            throw Exception("Wrong Triangle")

        val legs: List<Line> = listOf(rightAngle.angleNode.startLine!!, rightAngle.angleNode.finalLine!!)
        val hypotenuse = rightAngle.startNode.startLine!!

        for (i in 0..1) {
            legs[i].onKnownFunList.add { oneLeg ->
                val twoLeg = legs[(i + 1) % 2]

                val valueGetter: (Float?) -> Float? = {sqrt(oneLeg.getValue()!!.pow(2) + twoLeg.getValue()!!.pow(2))}

                if (twoLeg.getValue() != null && hypotenuse.getValue() == null)
                    hypotenuse.setDependentValueGraph(
                        valueGetter,
                        listOf(oneLeg, twoLeg),
                        formatSolve(
                            R.string.verbal_triangle_right_pythagorean_theorem_3,
                            hypotenuse.toString(),
                            oneLeg.toString(),
                            twoLeg.toString()
                        ),
                        formatSolve(
                            R.string.expression_triangle_right_pythagorean_theorem_3,
                            hypotenuse.toString(),
                            oneLeg.toString(),
                            twoLeg.toString()
                        ),
                        {formatSolve(
                            R.string.expression_triangle_right_pythagorean_theorem_3,
                            valueGetter(0f).toString(),
                            oneLeg.getValue().toString(),
                            twoLeg.getValue().toString()
                        )}
                    )
            }
        }
    }
}