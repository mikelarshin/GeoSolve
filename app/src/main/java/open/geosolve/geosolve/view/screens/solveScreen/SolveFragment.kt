package open.geosolve.geosolve.view.screens.solveScreen

import android.text.style.TextAppearanceSpan
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_solve.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.SolveScreenPresenter
import open.geosolve.geosolve.presentation.view.SolveScreenView
import open.geosolve.geosolve.view.MvpFragmentX

class SolveFragment : MvpFragmentX(R.layout.fragment_solve), SolveScreenView {

    private val presenter by moxyPresenter { SolveScreenPresenter(app) }

    override fun setupLayout() {

        layout.back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        setupRecycler()
    }

    private fun setupRecycler() {
        layout.recycler.adapter = RecycleAdapter()
        layout.recycler.layoutManager = LinearLayoutManager(activity)
        layout.recycler.addItemDecoration(DividerItemDecoration(activity))
    }
}
