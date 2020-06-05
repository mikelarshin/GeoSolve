package open.geosolve.geosolve.model

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

        val alphabet = ('A'..'Z').toList()
        allNodes.filter { it !in circleNodeList }.forEachIndexed { index, node ->
            if (node !in circleNodeList)
                node.char = alphabet[index].toString()
        }

        // TODO(create char AA AB AC)
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
