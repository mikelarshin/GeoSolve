package open.geosolve.geosolve.presentation.canvas

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface CanvasScreenView : MvpView {
    fun goToSolveScreen()
    fun updateCanvas()
    fun showDialog(titleID: Int, inputCallback: (value: Float) -> Unit)
    fun showTypeFigure()
    fun showMessage(messageID: Int)
}