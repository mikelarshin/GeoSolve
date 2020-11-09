package open.geosolve.geosolve.view.solve

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_solve.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.presentation.solve.SolveScreenPresenter
import open.geosolve.geosolve.presentation.solve.SolveScreenView
import open.geosolve.geosolve.view.MvpFragmentX
import open.geosolve.geosolve.view.solve.SolveFragmentArgs.fromBundle
import open.geosolve.geosolve.view.DividerItemDecoration

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

        val args = fromBundle(requireArguments())
        val solveList = args.solveList as List<SolveGraph>
        adapter.fill(solveList.map { it.stepSolve })
        layout.solveCanvasView.dataCanvas = args.dataCanvas
    }
}
