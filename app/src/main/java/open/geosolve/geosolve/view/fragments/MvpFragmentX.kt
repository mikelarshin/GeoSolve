package open.geosolve.geosolve.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import open.geosolve.geosolve.view.inflate

open class MvpFragmentX(val layoutRes: Int) : MvpAppCompatFragment() {

    protected lateinit var layout: View

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        layout = inflater.inflate(layoutRes, container, false)
        setupLayout()
        savedInstanceState?.let { setupData(it) }

        return layout
    }

    protected open fun setupLayout() {}
    protected open fun setupData(bundle: Bundle) {}
    protected open fun saveData(bundle: Bundle) {}

    override fun onSaveInstanceState(outState: Bundle) {
        saveData(outState)
        super.onSaveInstanceState(outState)
    }
}