package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure

object Rectangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 4 && figure.mLines.size == 4
                && figure.mAngles.filter {it.getValue() == 90f}.size >= 2
    }

    override fun setGraphs(figure: Figure) {
        for (i in 0..3)
            figure.mLines[i].onKnownFun = { thisElement ->
                if ((thisElement.getValue() != null) and (figure.mLines[(i + 2) % 4].getValue() == null))
                    figure.mLines[(i + 2) % 4].setDependentValueGraph(
                        figure.mLines[i].getLinkValue(),
                        listOf(figure.mLines[i])
                    )
            }
    }
}

