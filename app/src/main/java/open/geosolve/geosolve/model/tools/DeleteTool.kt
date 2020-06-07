package open.geosolve.geosolve.model.tools

object DeleteTool : BaseTool() {
    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot)
            selectElement?.remove()

        super.onTouchUp(x, y)
    }
}