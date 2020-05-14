package open.geosolve.geosolve

import android.app.Application
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE


class App : Application() {
    companion object {
        lateinit var instance: App
            private set
        var figureList: MutableList<Figure> = mutableListOf(Figure())
        var find: SolveGraph? = null

        val allNodes: List<Node>
            get() = figureList.flatMap { it.mNodes }.toMutableList()
        val allLines: List<Line>
            get() = figureList.flatMap { it.mLines }.toMutableList()
        val allAngles: List<Angle>
            get() = figureList.flatMap { it.mAngles }.toMutableList()
        val allCircles: List<Circle>
            get() = figureList.flatMap { listOf(it.mCircle) }.filterNotNull().toMutableList()

        val scale get() = (heightCanvas + widthCanvas) / 2 / POINT_SIZE // 1 coordinate unit equals 1 Node

        var widthCanvas: Int = 0
        var heightCanvas: Int = 0

        var systemCoordinate: SystemCoordinate = SystemCoordinate.DECART

        fun delElement(element: Element) {
            for (figure in figureList) {
                if (figure.mLines.contains(element))
                    figure.mLines.remove(element)
                if (figure.mAngles.contains(element))
                    figure.mAngles.remove(element)
                if (figure.mNodes.contains(element))
                    figure.mNodes.remove(element)
                if (figure.mCircle == element)
                    figure.mCircle = null
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}