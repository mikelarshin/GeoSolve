package com.example.geosolve.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geosolve.MainActivity
import com.example.geosolve.Presenter
import com.example.geosolve.R
import kotlinx.android.synthetic.main.canvas_fragment.view.*
import kotlinx.android.synthetic.main.dialog.view.*


class CanvasFragment : Fragment() {

    var presenter: Presenter = MainActivity.presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.canvas_fragment, container, false)

        // Я понимаю что это тупой подход и так Presenter делать не надо, просто лень
        presenter.canvasView = view.canvas
        presenter.calcButton = view.calc_button
        presenter.clearButton = view.clear_button
        presenter.editModeButton = view.edit_mode_button
        presenter.deleteModeButton = view.delete_mode_button
        presenter.markModeButton = view.mark_mode_button
        presenter.setValueModeButton = view.set_value_mode_button
        presenter.canvasFragment = this

        presenter.setCanvasButton()

        return view
    }

    fun onDialog(name: String, elem: Any?) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("Введи значение $name.")

        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog, null)

        builder.setView(view)

        builder
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                presenter.setValue(elem, view.input_text.text.toString().toFloat())
            }
            .setNegativeButton("Отмена") { dialog, id ->
                dialog.dismiss()
            }

        builder.create().show()
    }
}