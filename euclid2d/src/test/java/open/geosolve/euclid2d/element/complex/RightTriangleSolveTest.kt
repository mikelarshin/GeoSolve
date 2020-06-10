package open.geosolve.euclid2d.element.complex

import open.geosolve.euclid2d.element.complex.triangle.RightTriangle
import open.geosolve.euclid2d.element.primitive.metric.Degree
import open.geosolve.euclid2d.graph.triangle.RightTriangleSolveTree
import open.geosolve.euclid2d.helper.TestHelper.description
import open.geosolve.euclid2d.helper.TestHelper.ok
import open.geosolve.euclid2d.helper.TestHelper.test
import open.geosolve.euclid2d.helper.initOnce
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RightTriangleSolveTest {

    companion object {
        private const val LEG_SIZE = 5f
    }

    private var polygon by initOnce<Polygon>()

    @BeforeAll
    fun `Create triangle`() {

        polygon = Polygon().apply {
            appendPoint(0f, 0f)
            appendPoint(0f, LEG_SIZE)
            appendPoint(LEG_SIZE, 0f)
            close()
        }

        // FIXME(CRUNCH) Auto calculate angles
        polygon.lines.forEach { it.autoCalculateLength() }
        polygon.angles.first().value = Degree(degree = 90f)

        print(polygon)
    }

    @BeforeAll
    fun init() {


    }

    @Test
    @Order(0)
    fun `Find area by legs`() {

        test("Вычисление площади по катету")
        description("S = 1/2 * a * b, где a, b это катеты")

        val n = RightTriangleSolveTree.SquareByLegs(RightTriangle(polygon))

        assert(n.isDependenciesResolved())
        ok("Зависимости удовлетворены")

        ok("Вычисленное значение: ${n.calculate()}")
    }
}