package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.model.solve.SolveUtil.subTypeSolve
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatSolve
import java.lang.Exception

object Triangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 3 && figure.mLines.size == 3 && figure.mAngles.size == 3
    }

    override fun setGraphs(figure: Figure) {
        if (figure.mAngles.sumByDouble { (it.getValue() ?: 0f).toDouble() } > 180.0)
            throw Exception("Received triangle with sum of known angle higher 180 TODO()") // TODO say this for user
        if (figure.mAngles.sumByDouble { (it.getValue() ?: 0f).toDouble() } != 180.0
            && figure.mAngles.none { it.getValue() == null })
            throw Exception("Received triangle with sum of all angle not equal 180 TODO()") // TODO say this for user

        for (i in 0..2)
            figure.mAngles[i].onKnownFunList.add { thisElement ->
                if (figure.mAngles.filter { it.getValue() != null }.size == 2) {

                    val known_angles = figure.mAngles.filter { it.getValue() != null }
                    val valueGetter: (Float?) -> Float? = {180 - (known_angles[0].getValue()!! + known_angles[1].getValue()!!)}
                    val unknown_angle = figure.mAngles.filter { it.getValue() == null }[0]

                    figure.mAngles.first { it.getValue() == null }.setDependentValueGraph(
                        valueGetter,
                        known_angles,
                        formatSolve(
                            R.string.verbal_triangle_2_known_angle_1_unknown_2,
                            R.string.expression_triangle_2_known_angle_1_unknown_3,
                            unknown_angle,
                            known_angles[0],
                            known_angles[1]
                        )
                    )
                }
            }

        subTypeSolve = when {
            RightTriangle.isMatch(figure) -> RightTriangle
            else -> UnknownFigure
        }

        subTypeSolve.setGraphs(figure)
    }
}