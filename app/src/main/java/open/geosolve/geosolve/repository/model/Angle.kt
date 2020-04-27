package open.geosolve.geosolve.repository.model

class Angle(val startNode: Node, val angleNode: Node, val finalNode: Node) : Element() {
    // all logic solve in abstract Element

    init {
        if (startNode == finalNode || startNode == angleNode || angleNode == finalNode)
            throw Exception("Angle constructor get the same Node")
    }
}