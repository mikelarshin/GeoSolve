package open.geosolve.geosolve.presentation.canvas

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface CanvasScreenView : MvpView {

    fun updateCanvas()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFigureType()

    fun showInputDialog(
        @StringRes titleRes: Int,
        callback: (value: Float) -> Unit
    )

    fun showMessage(message: String)

    fun goToSolveScreen()
}