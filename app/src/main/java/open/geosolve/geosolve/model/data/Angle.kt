package open.geosolve.geosolve.model.data

class Angle(
    private val startLine: Line,
    private val finalLine: Line
) : Element() {

    override fun toString() =
        "∠${(startNode.char.toString() + angleNode.char + finalNode.char)}"

    val startNode: Node
        get() = startLine.startNode

    val angleNode: Node
        get() = startLine.finalNode

    val finalNode: Node
        get() = finalLine.finalNode

    init {
        when {
            startNode == finalNode ->
                throw Exception("Angle constructor get the same startNode & finalNode")

            startNode == angleNode ->
                throw Exception("Angle constructor get the same startNode & angleNode")

            angleNode == finalNode ->
                throw Exception("Angle constructor get the same angleNode & finalNode")

            startLine == finalLine ->
                throw Exception("Angle constructor get the same Line")

            startLine.finalNode != finalLine.startNode ->
                throw Exception("Angle constructor get wrong Line")
        }
    }

    fun deleteConnections() {
        startNode.startAngle = null
        angleNode.centerAngle = null
        finalNode.finalAngle = null
    }
}