package open.geosolve.geosolve

import android.app.Application

class App : Application() { // костыльный класс только для того чтобы получать color и string из resources
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}