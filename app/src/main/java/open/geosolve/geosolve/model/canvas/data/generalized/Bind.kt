package open.geosolve.geosolve.model.canvas.data.generalized

import open.geosolve.geosolve.model.canvas.data.elements.Node
import open.geosolve.geosolve.model.canvas.math.XYPoint

interface Bind {
    fun onBindNodeXY(node: Node, newPoint: XYPoint)
    val bindNodes: MutableSet<Node>
    fun updateAllBind() = bindNodes.forEach { it.updateXYbyBind() }
    fun moveEvent() = updateAllBind()
}