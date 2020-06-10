package open.geosolve.euclid2d.graph

abstract class SolveNode {
    abstract fun isDependenciesResolved(): Boolean
    abstract fun calculate(): Float
}