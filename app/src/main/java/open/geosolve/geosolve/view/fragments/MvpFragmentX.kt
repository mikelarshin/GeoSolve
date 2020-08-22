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
        savedInstanceState?.let { setupData(it) }

        return layout
    }

    protected abstract fun setupLayout()
    protected open fun saveData(bundle: Bundle) {}
    protected open fun setupData(bundle: Bundle) {}

    override fun onSaveInstanceState(outState: Bundle) {
        saveData(outState)
        super.onSaveInstanceState(outState)
    }
}