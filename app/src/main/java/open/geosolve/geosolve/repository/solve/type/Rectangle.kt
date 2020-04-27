package open.geosolve.geosolve.repository.solve.type

import open.geosolve.geosolve.repository.model.Figure
import open.geosolve.geosolve.repository.solve.SolveFigure

object Rectangle : SolveFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 4 && figure.mLines.size == 4
                && figure.mAngles.filter { it.getValue() == 90f || it.getValue() == null }.size == 4
    }

    override fun setGraphs(figure: Figure) {
        for (i in 0..3)
            figure.mLines[i].onKnownFun = {
                if ((figure.mLines[i].getValue() != null) and (figure.mLines[(i + 2) % 4].getValue() == null))
                    figure.mLines[(i + 2) % 4].setValueGraph(
                        figure.mLines[i].getValue(),
                        listOf(figure.mLines[i])
                    )
            }
    }
}

