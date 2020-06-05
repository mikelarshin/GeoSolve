package open.geosolve.geosolve.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import open.geosolve.geosolve.GlobalFiguresController
import open.geosolve.geosolve.GlobalFiguresController.allCircles
import open.geosolve.geosolve.GlobalFiguresController.allNodes
import open.geosolve.geosolve.model.ElementGetter.getDeletableElement
import open.geosolve.geosolve.model.solve.SolveUtil

object EventControl {
    fun setNodeChars() {
        val circleNodeList = allCircles.map { it.centerNode }
        circleNodeList.forEach { it.char = "O" }

        val alphabet1 = listOf("") + (('A'..'Z').toList())
        val alphabet2 = ('A'..'Z').toList()

        val nodes = allNodes.filter { it !in circleNodeList }

        for (index in nodes.indices)
            nodes[index].char = "${alphabet1[index / 26]}${alphabet2[index % 26]}"
    }

    fun solve(solveUiCallBack: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            SolveUtil.solve(GlobalFiguresController.figureList)
            solveUiCallBack()
        }
    }

    fun delete(touchX: Float, touchY: Float) {
        getDeletableElement(touchX, touchY)?.remove()
    }
}
