package open.geosolve.geosolve.model

import open.geosolve.geosolve.App
import open.geosolve.geosolve.model.ElementGetter.getDeleteElement

object DrawControl {
    fun delNode(touchX: Float, touchY: Float) {
        getDeleteElement(touchX, touchY)?.let { element ->
            element.delConnection()
            App.delElementFromFigure(element)
        }
    }
}