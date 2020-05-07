package open.geosolve.geosolve.model.data

class Angle(val startLine: Line, val finalLine: Line) : Element() {

    // all logic solve in abstract Element

    override fun toString(): String = "∠${(startNode.char.toString() + angleNode.char + finalNode.char)}"

    val startNode: Node
        get() = startLine.startNode
    val angleNode: Node
        get() = startLine.finalNode
    val finalNode: Node
        get() = finalLine.finalNode

    init {
        if (startNode == finalNode)
            throw Exception("Angle constructor get the same startNode & finalNode")
        if (startNode == angleNode)
            throw Exception("Angle constructor get the same startNode & angleNode")
        if (angleNode == finalNode)
            throw Exception("Angle constructor get the same angleNode & finalNode")
        if (startLine == finalLine)
            throw Exception("Angle constructor get the same Line")
        if (startLine.finalNode != finalLine.startNode)
            throw Exception("Angle constructor get wrong Line")
    }

    override fun delConnection(){
        startNode.startAngle = null
        angleNode.centerAngle = null
        finalNode.finalAngle = null
    }
}