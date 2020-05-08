package open.geosolve.geosolve.ui.solve

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_solve.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.solve.CallBackSolveUi
import open.geosolve.geosolve.model.solve.SolveUtil
import open.geosolve.geosolve.presentation.solve.SolveScreenPresenter
import open.geosolve.geosolve.presentation.solve.SolveScreenView
import open.geosolve.geosolve.ui.global.MvpFragmentX
import open.geosolve.geosolve.ui.global.recyclerview.DividerItemDecoration

class SolveFragment : MvpFragmentX(R.layout.fragment_solve), SolveScreenView {

    private val presenter by moxyPresenter { SolveScreenPresenter() }

    override fun setupLayout() {
        setupListeners()
        setupRecycler()
        showSolve()
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

    private fun showSolve() {
        SolveUtil.showStepSolveList(app.figure, object : CallBackSolveUi {

            override fun findNotMark() {
                throw RuntimeException("Find not mark")
            }

            override fun solveIsNotFound() {
                Log.d("GeoSolve", "Решение не найдено")
            }

            override fun userInputValue() {
                Log.d("GeoSolve", "Значение задано пользователем")
            }

            override fun solveIsFound() {
                Log.d("GeoSolve", "Значение найдено")
            }
        })
    }
}
