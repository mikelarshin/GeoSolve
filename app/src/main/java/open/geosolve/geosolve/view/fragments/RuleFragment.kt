package open.geosolve.geosolve.view.fragments

import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.RuleScreenPresenter
import open.geosolve.geosolve.presentation.view.RuleScreenView

class RuleFragment : MvpFragmentX(R.layout.fragment_rule), RuleScreenView {

    private val presenter by moxyPresenter { RuleScreenPresenter() }

    override fun setupLayout() {

    }
}