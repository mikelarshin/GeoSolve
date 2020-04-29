package open.geosolve.geosolve.model.data

class Angle(private val startLine: Line, private val finalLine: Line) : Element() {
    // all logic solve in abstract Element

    val startNode: Node
        get() = startLine.startNode
    val angleNode: Node
        get() = startLine.finalNode
    val finalNode: Node
        get() = finalLine.finalNode

    init {
        if (startNode == finalNode)
            throw Exception("Angle constructor get the same Node")
        if (startNode == angleNode)
            throw Exception("Angle constructor get the same Node")
        if (angleNode == finalNode)
            throw Exception("Angle constructor get the same Node")
        if (startLine.finalNode != finalLine.startNode)
            throw Exception("Angle constructor get wrong Line") // TODO(Fix this)
    }

    fun delConnection(){
        startNode.startAngle = null
        angleNode.centerAngle = null
        finalNode.finalAngle = null
    }
}