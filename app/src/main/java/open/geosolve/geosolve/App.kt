package open.geosolve.geosolve

import android.app.Application
import open.geosolve.geosolve.model.data.Figure

class App : Application() {

    var figure = Figure()

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}