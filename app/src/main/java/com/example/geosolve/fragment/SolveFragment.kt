package com.example.geosolve.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geosolve.MainActivity
import com.example.geosolve.R
import com.example.geosolve.StepSlove
import com.example.geosolve.view.RecycleAdapter
import kotlinx.android.synthetic.main.solve_fragment.*
import kotlinx.android.synthetic.main.solve_fragment.view.*
import kotlinx.android.synthetic.main.solve_fragment.view.recycler

class SolveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.solve_fragment, container, false)

        view.back_button.setOnClickListener {
            MainActivity.navControler.popBackStack()
        }

        view.recycler.layoutManager = LinearLayoutManager(view.context)
        view.recycler.setHasFixedSize(true)

        view.recycler.adapter = RecycleAdapter(view.context)

        view.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && view.back_button.isShown)
                    view.back_button.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    view.back_button.show();

                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        for (i in 1..100)
            RecycleAdapter.addItem(
                StepSlove(
                    "%s + %s = %s",
                    "Сложение",
                    i.toString(), i.toString(), (i + i).toString()
                )
            )

        return view
    }
}
