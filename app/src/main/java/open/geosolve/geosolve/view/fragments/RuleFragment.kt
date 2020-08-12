package open.geosolve.geosolve.view.fragments

import kotlinx.android.synthetic.main.fragment_rule.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.RuleScreenPresenter
import open.geosolve.geosolve.presentation.view.RuleScreenView
import open.geosolve.geosolve.view.DesignUtil
import open.geosolve.geosolve.view.rules.Rule

class RuleFragment : MvpFragmentX(R.layout.fragment_rule), RuleScreenView {

    private val presenter by moxyPresenter { RuleScreenPresenter() }

    companion object {
        lateinit var rule: Rule // CRUNCH
    }

    override fun setupLayout() {
        layout.rule_title.text = DesignUtil.formatRuleTitle(rule)
        layout.rule_text.text = DesignUtil.formatRuleText(rule)
    }
}