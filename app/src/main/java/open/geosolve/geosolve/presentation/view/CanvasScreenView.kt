package open.geosolve.geosolve.presentation.view

import android.graphics.Canvas
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.views.canvas.CanvasData

// TODO Presenter sets ui vars!!!
@StateStrategyType(OneExecutionStateStrategy::class)
interface CanvasScreenView : MvpView {
    fun goToSolveScreen(solveList: List<SolveGraph>)
    fun updateCanvas()
    fun showDialog(titleID: Int, element: String, inputCallback: (value: Float) -> Unit)
    fun showTypeFigure()
    fun showMessage(messageID: Int)
    val dataCanvas: CanvasData
}