package open.geosolve.geosolve.repository.model

class Angle(
    val startNode: Node,
    val angleNode: Node,
    val finalNode: Node
) : Element {
    var value: Float? = null
}