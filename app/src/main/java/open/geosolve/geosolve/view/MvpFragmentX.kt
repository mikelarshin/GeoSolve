package open.geosolve.geosolve.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment

open class MvpFragmentX(val layoutRes: Int) : MvpAppCompatFragment() {

    protected lateinit var layout: View

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        layout = inflater.inflate(layoutRes, container, false)
        setupLayout()
        savedInstanceState?.let { setupData() }

        return layout
    }

    protected open fun setupLayout() {}
    protected open fun setupData() {}
    protected open fun saveData() {}

    override fun onSaveInstanceState(outState: Bundle) {
        saveData()
        super.onSaveInstanceState(outState)
    }
}