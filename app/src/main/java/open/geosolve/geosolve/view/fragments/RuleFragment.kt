package open.geosolve.geosolve.view.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_rule.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.RuleScreenPresenter
import open.geosolve.geosolve.presentation.view.RuleScreenView
import open.geosolve.geosolve.view.rules.Rule
import open.geosolve.geosolve.view.views.recyclers.RuleRecyclerAdapter

class RuleFragment : MvpFragmentX(R.layout.fragment_rule), RuleScreenView {

    private val presenter by moxyPresenter { RuleScreenPresenter() }
    private val adapter = RuleRecyclerAdapter()
    private lateinit var rule: Rule

    override fun setupLayout() {
        layout.ruleRecycler.adapter = adapter
        layout.ruleRecycler.layoutManager = LinearLayoutManager(activity)

        rule = RuleFragmentArgs.fromBundle(arguments!!).rule
        adapter.fill(rule.ruleItems)
    }
}


