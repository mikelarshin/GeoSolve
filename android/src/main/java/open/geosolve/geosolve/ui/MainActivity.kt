package open.geosolve.geosolve.ui

import android.os.Bundle
import moxy.MvpAppCompatActivity
import open.geosolve.geosolve.R

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
