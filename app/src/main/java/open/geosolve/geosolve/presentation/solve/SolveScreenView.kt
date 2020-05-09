package open.geosolve.geosolve.presentation.solve

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SolveScreenView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoading()

    fun showContent()
    fun showError()
    fun showUnknown()
}