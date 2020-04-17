package com.example.geosolve.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geosolve.MainActivity
import com.example.geosolve.Presenter
import com.example.geosolve.R
import kotlinx.android.synthetic.main.canvas_fragment.view.*

class CanvasFragment : Fragment() {

    var presenter: Presenter = MainActivity.presenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.canvas_fragment, container, false)

        presenter.canvasView = view.canvas
        presenter.calcButton = view.calc_button
        presenter.clearButton = view.clear_button
        presenter.editModeButton = view.edit_mode_button
        presenter.deleteModeButton = view.delete_mode_button
        presenter.markModeButton = view.mark_mode_button

        presenter.setCanvasButton()

        return view
    }
}