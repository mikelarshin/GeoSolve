package open.geosolve.geosolve.view.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.exemple_figure_item.view.*
import kotlinx.android.synthetic.main.fragment_solve.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.presentation.presenter.SolveScreenPresenter
import open.geosolve.geosolve.presentation.view.SolveScreenView
import open.geosolve.geosolve.view.fragments.SolveFragmentArgs.fromBundle
import open.geosolve.geosolve.view.views.recyclers.DividerItemDecoration
import open.geosolve.geosolve.view.views.recyclers.SolveRecyclerAdapter

class SolveFragment : MvpFragmentX(R.layout.fragment_solve), SolveScreenView {

    private val presenter by moxyPresenter { SolveScreenPresenter() }
    private val adapter = SolveRecyclerAdapter()

    override fun setupLayout() {

        layout.back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        setupRecycler()
    }

    private fun setupRecycler() {
        layout.solveRecycler.adapter = adapter
        layout.solveRecycler.layoutManager = LinearLayoutManager(activity)
        layout.solveRecycler.addItemDecoration(DividerItemDecoration(activity))

        val solveList = fromBundle(arguments!!).solveList as List<SolveGraph>
        adapter.fill(solveList)
    }
}
