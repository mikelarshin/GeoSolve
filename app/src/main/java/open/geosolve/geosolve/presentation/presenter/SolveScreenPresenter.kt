package open.geosolve.geosolve.presentation.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.App
import open.geosolve.geosolve.presentation.view.SolveScreenView

@InjectViewState
class SolveScreenPresenter(val app: App) : MvpPresenter<SolveScreenView>() {
}