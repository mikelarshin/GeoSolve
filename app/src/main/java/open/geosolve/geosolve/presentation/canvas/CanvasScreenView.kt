package open.geosolve.geosolve.presentation.canvas

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph

@StateStrategyType(OneExecutionStateStrategy::class)
interface CanvasScreenView : MvpView {
    fun goToSolveScreen(solveList: List<SolveGraph>)
    fun updateCanvas()
    fun showDialog(titleID: Int, element: String, inputCallback: (value: Float) -> Unit)
    fun showTypeFigure()
    fun showMessage(messageID: Int)
}