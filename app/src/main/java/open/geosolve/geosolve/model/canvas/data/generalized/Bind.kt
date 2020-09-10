package open.geosolve.geosolve.model.canvas.data.generalized

import open.geosolve.geosolve.model.canvas.data.Node

interface Bind {
    fun onBindNodeXY(node: Node, newX: Float, newY: Float)
    val bindNodes: MutableSet<Node>
    fun updateAllBind() = bindNodes.forEach { it.updateXYbyBind() }
    fun moveEvent() = updateAllBind()
}