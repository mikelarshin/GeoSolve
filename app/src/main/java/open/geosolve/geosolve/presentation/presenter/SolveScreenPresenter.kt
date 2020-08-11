package open.geosolve.geosolve.presentation.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.solve.CallBackSolveUi
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.view.SolveScreenView
import open.geosolve.geosolve.view.views.recycler.RecycleAdapter

@InjectViewState
class SolveScreenPresenter : MvpPresenter<SolveScreenView>()