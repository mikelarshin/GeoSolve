package open.geosolve.geosolve.model.data

interface Bind {
    fun toBindNodeXY(node: Node, newX: Float, newY: Float)
    fun updateAllBind()
    val bindNodeList: MutableList<Node>
}