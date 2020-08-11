package open.geosolve.geosolve.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment

abstract class MvpFragmentX(private val layoutRes: Int) : MvpAppCompatFragment() {

    protected lateinit var layout: View

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        layout = inflater.inflate(layoutRes, container, false)
        setupLayout()

        return layout
    }

    protected abstract fun setupLayout()
}