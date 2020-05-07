package open.geosolve.geosolve.presentation.canvas

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CanvasScreenView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun updateCanvas()

    fun showFigureType()

    fun showInputDialog(
        @StringRes titleRes: Int,
        callback: (value: Float) -> Unit
    )
}