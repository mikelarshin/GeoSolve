package open.geosolve.geosolve.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import open.geosolve.geosolve.App

abstract class MvpFragmentX(
    private val layoutRes: Int
) : MvpAppCompatFragment() {

    protected lateinit var layout: View

    protected val activity: MvpAppCompatActivity
        get() = getActivity() as MvpAppCompatActivity

    protected val app: App
        get() = activity.application as App

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        layout = inflater.inflate(layoutRes, container, false)
        setupLayout()

        return layout
    }

    protected abstract fun setupLayout()
}