package open.geosolve.geosolve

import android.app.Application
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.properties.Delegates


class App : Application() {
    companion object {
        lateinit var instance: App
            private set
        var figureList: MutableList<Figure> = mutableListOf(Figure())
        var find: Element? = null

        val allNodes: MutableList<Node>
            get() = figureList.flatMap { it.mNodes }.toMutableList()
        val allLines: MutableList<Line>
            get() = figureList.flatMap { it.mLines }.toMutableList()
        val allAngles: MutableList<Angle>
            get() = figureList.flatMap { it.mAngles }.toMutableList()
        val allCircles: MutableList<Circle>
            get() = figureList.flatMap { listOf(it.mCircle) }.filterNotNull().toMutableList()

        val scaleForY get() = heightCanvas / POINT_SIZE // 1 coordinate unit equals 1 Node
        val scaleForX get() = widthCanvas / POINT_SIZE // 1 coordinate unit equals 1 Node

        var widthCanvas by Delegates.notNull<Int>()
        var heightCanvas by Delegates.notNull<Int>()

        var systemCoordinate: SystemCoordinate = SystemCoordinate.DECART
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}