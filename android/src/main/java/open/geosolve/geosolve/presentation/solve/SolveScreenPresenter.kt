package open.geosolve.geosolve.presentation.solve

import kotlinx.coroutines.launch
import moxy.InjectViewState
import open.geosolve.geosolve.App
import open.geosolve.geosolve.model.data.Element
import open.geosolve.geosolve.model.solve.SolveCallback
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.global.MvpPresenterX

@InjectViewState
class SolveScreenPresenter(
    private val app: App
) : MvpPresenterX<SolveScreenView>() {

    var solveSteps: List<Element> = listOf()
        private set

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        launch {
            SolveUtil.solve(app.figure)
            SolveUtil.getSolveSteps(app.figure, object : SolveCallback {

                override fun noSearchedElement() {
                    throw RuntimeException("Searched element not presented")
                }

                override fun notFound() {
                    viewState.showUnknown()
                }

                override fun userValue() {
                    viewState.showError()
                }

                override fun found(solveSteps: List<Element>) {
                    this@SolveScreenPresenter.solveSteps = solveSteps
                    viewState.showContent()
                }
            })
        }
    }
}