package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.App
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.math.hypot

class Circle(val centerNode: Node) : Element(), Movable {

    // all logic solve in abstract Element

    var getDrawRadius: () -> Float = {0f} // динамически изменяющийся метод из которого мы получаем отрисовывающийся радиус в зависимости от системы координат

    override fun delConnection() {
        TODO("Not yet implemented")
    }

    override fun move(x: Float, y: Float) {
        getDrawRadius = {hypot(centerNode.x - App.systemCoordinate.transformationMethodX(x),
            centerNode.y - App.systemCoordinate.transformationMethodY(y))}
    }

    fun inRadiusLine(x: Float, y: Float): Boolean {
        val distanceToCenter = hypot(centerNode.x - App.systemCoordinate.transformationMethodX(x),
            centerNode.y - App.systemCoordinate.transformationMethodY(y))

        val distanceToCircle =
            if (getDrawRadius() < distanceToCenter)
                distanceToCenter - getDrawRadius()
            else
                getDrawRadius() - distanceToCenter

        val bufferUseTouch = POINT_SIZE / 20
        // FIXME(не всегда детектит нажатие на окружность)
        return distanceToCircle < bufferUseTouch
    }
}