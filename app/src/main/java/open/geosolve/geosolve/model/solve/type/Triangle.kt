package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.model.solve.SolveUtil.subTypeSolve
import open.geosolve.geosolve.ui.solve.DesignUtil.formatSolve

object Triangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.nodes.size == 3 && figure.lines.size == 3 && figure.angles.size == 3
    }

    override fun setGraphs(figure: Figure) {
        if (figure.angles.sumByDouble { (it.getValue() ?: 0f).toDouble() } > 180.0)
            throw Exception("Received triangle with sum of known angle higher 180 TODO()") // TODO say this for user
        if (figure.angles.sumByDouble { (it.getValue() ?: 0f).toDouble() } != 180.0
            && figure.angles.none { it.getValue() == null })
            throw Exception("Received triangle with sum of all angle not equal 180 TODO()") // TODO say this for user

        for (i in 0..2)
            figure.angles[i].onKnownFunList.add { thisElement ->
                if (figure.angles.filter { it.getValue() != null }.size == 2) {

                    val known_angles = figure.angles.filter { it.getValue() != null }
                    val valueGetter: (Float?) -> Float? = {180 - (known_angles[0].getValue()!! + known_angles[1].getValue()!!)}
                    val unknown_angle = figure.angles.filter { it.getValue() == null }[0]

                    figure.angles.filter { it.getValue() == null }[0].setDependentValueGraph(
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