package open.geosolve.geosolve.model.data

import open.geosolve.geosolve.App
import open.geosolve.geosolve.model.status.SystemCoordinate
import open.geosolve.geosolve.view.view.DrawCanvasView.Companion.POINT_SIZE
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.sqrt

class Circle(val centerNode: Node) : Element(), Movable, Bind {

    init {
        centerNode.char = 'O'
    }

    // all logic solve in abstract Element
    var radiusLineList: MutableList<Line> = mutableListOf()

    var drawRadius: Float = 0f
    private var decartRadius: Float = 0f // TODO(Rewrite this)

    override fun delConnection() {
        TODO("Not yet implemented")
    }

    // Bind
    override val bindNodeList: MutableList<Node> = mutableListOf()

    override fun updateAllBind() {
        bindNodeList.forEach { it.updateXYbyBind() }
    }

    override fun toBindNodeXY(node: Node, newX: Float, newY: Float) {
        node.x = centerNode.x + (decartRadius * (newX - centerNode.x) /
                sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2)))

        node.y = centerNode.y + (decartRadius * (newY - centerNode.y) /
                sqrt((newX - centerNode.x).pow(2) + (newY - centerNode.y).pow(2)))
    }

    override fun move(x: Float, y: Float) {
        App.systemCoordinate = SystemCoordinate.ABSOLUTE
        drawRadius = hypot(
            centerNode.x - App.systemCoordinate.transformationMethodX(x),
            centerNode.y - App.systemCoordinate.transformationMethodY(y)
        )
        App.systemCoordinate = SystemCoordinate.DECART
        decartRadius = hypot(
            centerNode.x - App.systemCoordinate.transformationMethodX(x),
            centerNode.y - App.systemCoordinate.transformationMethodY(y)
        ) // TODO(Rewrite this)

        updateAllBind()
        // так как drawRadius это значение для отрисовки высчитывающееся статически, мы записываем его с помошью Абсолютной системе координат
    }

    fun inRadiusLine(x: Float, y: Float): Boolean {
        val distanceToCenter = hypot(
            centerNode.x - App.systemCoordinate.transformationMethodX(x),
            centerNode.y - App.systemCoordinate.transformationMethodY(y)
        )

        val distanceToCircle =
            if (decartRadius < distanceToCenter)
                distanceToCenter - decartRadius
            else
                decartRadius - distanceToCenter

        val bufferUseTouch = POINT_SIZE / 20
        // FIXME(не всегда детектит нажатие на окружность)
        return distanceToCircle < bufferUseTouch
    }
}