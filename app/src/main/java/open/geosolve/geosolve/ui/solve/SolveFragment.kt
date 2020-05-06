package open.geosolve.geosolve.ui.solve

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_solve.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.solve.SolveScreenPresenter
import open.geosolve.geosolve.presentation.solve.SolveScreenView
import open.geosolve.geosolve.ui.global.MvpFragmentX
import open.geosolve.geosolve.ui.global.recyclerview.DividerItemDecoration

class SolveFragment : MvpFragmentX(R.layout.fragment_solve), SolveScreenView {

    private val presenter by moxyPresenter { SolveScreenPresenter() }

    override fun setupLayout() {
        setupListeners()
        setupRecycler()
    }

    private fun setupListeners() {
        layout.back_button.setOnClickListener {
            findNavController().popBackStack()
            RecycleAdapter.clear()
        }
    }

    private fun setupRecycler() {

        layout.recycler.adapter = RecycleAdapter()
        layout.recycler.layoutManager = LinearLayoutManager(activity)

        layout.recycler.addItemDecoration(DividerItemDecoration(context))
    }
}
