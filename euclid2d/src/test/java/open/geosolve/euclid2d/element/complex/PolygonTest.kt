package open.geosolve.euclid2d.element.complex

import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolygonTest {

    private val polygon: Polygon = Polygon()

    @Test
    @Order(1)
    fun `Create triangle`() {
        polygon.appendPoint(0f, 0f)
        polygon.appendPoint(0f, 5f)
        polygon.appendPoint(5f, 0f)
        polygon.close()
    }

    @Test
    @Order(2)
    fun `Check close`() {
        assert(polygon.isPolygonClosed)
    }

    @Test
    @Order(3)
    fun `Check lines`() {

        print(polygon)

        assert(polygon.lines[0].length == 5f)
        assert(polygon.lines[1].length == 7.071068f)
        assert(polygon.lines[2].length == 5f)
    }
}
