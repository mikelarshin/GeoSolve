package open.geosolve.geosolve.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import open.geosolve.geosolve.App

abstract class MvpFragmentX(
    private val layoutRes: Int
) : MvpAppCompatFragment() {

    protected lateinit var layout: View

    protected val activity: MvpAppCompatActivity?
        get() = super.getActivity() as MvpAppCompatActivity?

    protected val app: App
        get() = super.getActivity()!!.application as App

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layout = inflater.inflate(layoutRes, container, false)
        setupLayout()
        return layout
    }

    protected abstract fun setupLayout()

    protected fun unimplemented() {
        Toast.makeText(context, "\uD83D\uDE48 Unimplemented", Toast.LENGTH_LONG).show()
    }

    protected fun finish() {
        activity?.supportFragmentManager?.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
}