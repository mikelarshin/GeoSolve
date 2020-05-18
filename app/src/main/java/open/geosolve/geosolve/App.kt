package open.geosolve.geosolve

import android.app.Application
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.data.generalized.Element
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.draw.PaintConstant.POINT_SIZE

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
        var figureList: MutableList<Figure> = mutableListOf(Figure())
        var find: SolveGraph? = null

        val allNodes: Set<Node>
            get() = figureList.flatMap { it.mNodes }.toSet()
        val allLines: Set<Line>
            get() = figureList.flatMap { it.mLines }.toSet()
        val allAngles: Set<Angle>
            get() = figureList.flatMap { it.mAngles }.toSet()
        val allCircles: Set<Circle>
            get() = figureList.flatMap { listOf(it.mCircle) }.filterNotNull().toSet()

        val scale get() = ((heightCanvas + widthCanvas) / 2) / POINT_SIZE // 1 coordinate unit equals 1 Node

        var widthCanvas: Int = 0
        var heightCanvas: Int = 0

        var systemCoordinate: SystemCoordinate = SystemCoordinate.DECART

        fun delElementFromFigure(element: Element) {
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