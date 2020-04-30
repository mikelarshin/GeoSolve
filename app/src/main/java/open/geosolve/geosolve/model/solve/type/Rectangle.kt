package open.geosolve.geosolve.model.solve.type

import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.solve.SolveFigure
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil
import open.geosolve.geosolve.view.screens.solveScreen.DesignUtil.formatSolve

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
                        thisElement.getLinkValue(),
                        listOf(thisElement),
                        formatSolve(R.string.verbal_rectangle_parallel_line_2, "", ""),
                        formatSolve(R.string.expression_rectangle_parallel_line_2, "", ""))
            }

//        for (i in 0.. 3){
//            figure.mAngles[i].onKnownFun = {thisElement ->
//                for(j in 1..3)
//                    if (figure.mAngles[(i+j) % 4].getValue() != null)
//                        figure.mAngles[(i+j) % 4].setValueGraph(
//                            90f,
//                            emptyList(), formatSolve(), formatSolve())
//            }
//        }
    }
}

