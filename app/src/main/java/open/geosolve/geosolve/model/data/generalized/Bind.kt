package open.geosolve.geosolve.model.data.generalized

import open.geosolve.geosolve.model.data.Node

interface Bind {
    fun toBindNodeXY(node: Node, newX: Float, newY: Float)
    fun updateAllBind()
    val bindNodeList: MutableList<Node>
}