package open.geosolve.geosolve.view.fragments

import kotlinx.android.synthetic.main.fragment_rule.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.RuleScreenPresenter
import open.geosolve.geosolve.presentation.view.RuleScreenView
import open.geosolve.geosolve.view.DesignUtil
import open.geosolve.geosolve.view.DesignUtil.formatRuleText
import open.geosolve.geosolve.view.DesignUtil.formatRuleTitle
import open.geosolve.geosolve.view.rules.Rule

class RuleFragment : MvpFragmentX(R.layout.fragment_rule), RuleScreenView {

    private val presenter by moxyPresenter { RuleScreenPresenter() }
    private lateinit var rule: Rule

    override fun setupLayout() {
        rule = RuleFragmentArgs.fromBundle(arguments!!).rule

        layout.rule_title.text = formatRuleTitle(rule)
        layout.rule_text.text = formatRuleText(rule)
    }
}