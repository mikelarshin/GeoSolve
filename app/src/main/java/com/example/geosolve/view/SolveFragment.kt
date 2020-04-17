package com.example.geosolve.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.geosolve.MainActivity
import com.example.geosolve.Presenter
import com.example.geosolve.R
import com.example.geosolve.model.Solve
import com.example.geosolve.model.StepSlove
import kotlinx.android.synthetic.main.solve_fragment.*
import kotlinx.android.synthetic.main.solve_fragment.view.*
import kotlinx.android.synthetic.main.solve_fragment.view.recycler

class SolveFragment : Fragment() {

    var presenter: Presenter = MainActivity.presenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.solve_fragment, container, false)

        presenter.backButton = view.back_button
        presenter.recyclerView = view.recycler

        presenter.setRecyclerParam(view.context)
        presenter.setSolveButton()

        Solve(presenter.figure).start()

        return view
    }
}