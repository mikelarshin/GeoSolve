package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.GlobalFiguresController.find

object DeleteTool : BaseTool() {
    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot) {
            selectElement?.remove()

            if (find == selectElement)
                find = null
        }

        super.onTouchUp(x, y)
    }
}