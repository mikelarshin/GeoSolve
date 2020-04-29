package open.geosolve.geosolve.model.data

class Angle(val startNode: Node, val angleNode: Node, val finalNode: Node) : Element() {
    // all logic solve in abstract Element

    init {
        if (startNode == finalNode || startNode == angleNode || angleNode == finalNode)
            throw Exception("Angle constructor get the same Node")
    }

    fun delConnection(){
        startNode.startAngle = null
        angleNode.centerAngle = null
        finalNode.finalAngle = null
    }
}