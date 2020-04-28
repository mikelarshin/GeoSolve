package open.geosolve.geosolve.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

// TODO Presenter sets ui vars!!!
@StateStrategyType(OneExecutionStateStrategy::class)
interface CanvasScreenView : MvpView {
    fun goToCalculationFragment()
    fun updateCanvas()
    fun showDialog(title: String, okCallback: (value: Float) -> Unit)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTypeFigure()
}