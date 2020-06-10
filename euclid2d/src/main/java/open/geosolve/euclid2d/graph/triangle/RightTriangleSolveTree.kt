package open.geosolve.euclid2d.graph.triangle

import open.geosolve.euclid2d.element.complex.triangle.RightTriangle
import open.geosolve.euclid2d.graph.SolveNode


object RightTriangleSolveTree {

    // S = 1/2 * a * b
    class SquareByLegs(val rightTriangle: RightTriangle) : SolveNode() {

        override fun isDependenciesResolved(): Boolean {
            val legs = this.rightTriangle.legs
            return legs.first.length != null && legs.second.length != null
        }

        override fun calculate(): Float {

            check(isDependenciesResolved()) { "Dependencies not resolved" }

            val legs = this.rightTriangle.legs
            return 0.5f * legs.first.length!! * legs.second.length!!
        }
    }
}