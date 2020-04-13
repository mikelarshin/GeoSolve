package com.example.geosolve.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geosolve.MainActivity
import com.example.geosolve.R
import com.example.geosolve.view.CanvasView
import kotlinx.android.synthetic.main.canvas_fragment.view.*

class CanvasFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.canvas_fragment, container, false)

        view.calc_button.setOnClickListener{
            MainActivity.navControler.navigate(R.id.ToSolveAction)
        }

        view.clear_button.setOnClickListener{
            view.canvas.clear()
        }

        view.edit_mode_button.setOnClickListener{
            view.canvas.setMode("edit")
        }
        view.delete_mode_button.setOnClickListener{
            view.canvas.setMode("del")
        }


        return view
    }
}