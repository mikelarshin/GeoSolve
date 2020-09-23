package open.geosolve.geosolve.model.solve.types

import open.geosolve.geosolve.model.canvas.data.containers.Figure
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.solve.TypeFigure
import open.geosolve.geosolve.view.book.articles.polygon.quadrangle.ParallelLines
import open.geosolve.geosolve.view.book.articles.polygon.quadrangle.RightAngles

object Rectangle : TypeFigure(Parallelogram) {
    override fun isMatch(figure: Figure): Boolean =
        figure.mAngles.filter { it.getValue() == 90f }.size >= 2
                && figure.mAngles.none { it.getValue() != 90f && it.getValue() != null }


    override fun setGraphs(figure: Figure) {
        for (i in 0..3)
            figure.mLines[i].onKnownFunctions.add { knownElement ->
                if ((knownElement.getValue() != null) and (figure.mLines[(i + 2) % 4].getValue() == null))
                    figure.mLines[(i + 2) % 4].setDependentValueGraph(
                        { knownElement.getValue() },
                        listOf(knownElement),
                        ParallelLines.Step(knownElement as Line, figure.mLines[(i + 2) % 4])
                    )
            }

        for (i in 0..3) {
            figure.mAngles[i].onKnownFunctions.add { knownElement ->
                val anglesWithoutValue = figure.mAngles.filter { it.getValue() == null }
                val anglesWithValue = figure.mAngles.filter { it.getValue() != null }

                if (anglesWithoutValue.size <= 2)
                    anglesWithoutValue.forEach {
                        it.setValueGraph(
                            90f,
                            anglesWithValue,
                            RightAngles.Step(it)
                        )
                    }
            }
        }
    }
}

