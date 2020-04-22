package open.geosolve.geosolve.ui.screens.solveScreen

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_solve.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.repository.model.StepSolve
import open.geosolve.geosolve.ui.MvpFragmentX

class SolveFragment : MvpFragmentX(R.layout.fragment_solve) {

    override fun setupLayout() {

        setupRecycler()

        layout.back_button.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecycler() {

        val adapter = RecycleAdapter()

        (0..10).forEach {
            RecycleAdapter.addItem(
                StepSolve(
                    "Складываем значения",
                    "%s + %s = %s",
                    null,
                    it.toString(), it.toString(), (it + it).toString(),
                    context = app
                )
            )
        }

        layout.recycler.adapter = RecycleAdapter()
        layout.recycler.layoutManager = LinearLayoutManager(activity)
        layout.recycler.addItemDecoration(DividerItemDecoration(activity))
    }
}
