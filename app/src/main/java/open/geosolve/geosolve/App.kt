package open.geosolve.geosolve

import android.app.Application
import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.properties.Delegates


class App : Application() {
    companion object {
        lateinit var instance: App
            private set
        var figure = Figure()
        val scaleForY get() = heightCanvas / POINT_SIZE // 1 coordinate unit equals 1 Node
        val scaleForX get() = widthCanvas / POINT_SIZE // 1 coordinate unit equals 1 Node
        var systemCoordinate: SystemCoordinate = SystemCoordinate.DECART
        var widthCanvas by Delegates.notNull<Int>()
        var heightCanvas by Delegates.notNull<Int>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}