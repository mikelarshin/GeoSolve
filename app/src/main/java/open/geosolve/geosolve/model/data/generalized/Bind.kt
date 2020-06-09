package open.geosolve.geosolve.model.data.generalized

import open.geosolve.geosolve.model.data.Node

interface Bind {
    fun toBindNodeXY(node: Node, newX: Float, newY: Float)
    val bindNodes: MutableList<Node>
    fun updateAllBind() = bindNodes.forEach { it.updateXYbyBind() }
    fun moveEvent() = updateAllBind()
}