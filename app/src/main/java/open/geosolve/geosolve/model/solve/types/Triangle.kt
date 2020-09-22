package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.solve.TypeFigure
import open.geosolve.geosolve.view.book.articles.TriangleRules
import kotlin.math.round

object Triangle : TypeFigure(Polygon) {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 3 && figure.mLines.size == 3 && figure.mAngles.size == 3
    }

    override fun setGraphs(figure: Figure) {
        if (round(figure.mAngles.sumByDouble { (it.getValue() ?: 0f).toDouble() }) > 180.0)
            throw Exception("Received triangle with sum of known angle higher 180 TODO()") // TODO(say this for user)
        if (round(figure.mAngles.sumByDouble { (it.getValue() ?: 0f).toDouble() }) != 180.0
            && figure.mAngles.none { it.getValue() == null })
            throw Exception("Received triangle with sum of all angle not equal 180 TODO()") // TODO(say this for user)

        for (i in 0..2)
            figure.mAngles[i].onKnownFunctions.add { knownElement ->
                if (figure.mAngles.filter { it.getValue() != null }.size == 2) {

                    val known_angles = figure.mAngles.filter { it.getValue() != null }
                    val valueGetter: (Float?) -> Float? = {180 - (known_angles[0].getValue()!! + known_angles[1].getValue()!!)}
                    val unknown_angle = figure.mAngles.filter { it.getValue() == null }[0]

                    figure.mAngles.first { it.getValue() == null }.setDependentValueGraph(
                        valueGetter,
                        known_angles,
                        TriangleRules.know_2_unknown_1_angle.MyStep(unknown_angle, known_angles[0], known_angles[1]))
                }
            }
    }
}