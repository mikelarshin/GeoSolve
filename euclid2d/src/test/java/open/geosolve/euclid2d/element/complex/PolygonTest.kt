package open.geosolve.euclid2d.element.complex

import open.geosolve.euclid2d.helper.TestHelper.description
import open.geosolve.euclid2d.helper.TestHelper.ok
import open.geosolve.euclid2d.helper.TestHelper.test
import open.geosolve.euclid2d.helper.initOnce
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.math.pow
import kotlin.math.sqrt

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolygonTest {

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

        print(polygon)
    }

    @Test
    @Order(0)
    fun `Check close`() {

        test("Тест замкнутости")
        description("Проверка корректности замыкания треугольника")

        assert(polygon.isPolygonClosed)
        ok("Полигон замкнут")
    }

    @Test
    @Order(1)
    fun `Check lines`() {

        test("Проверка корректности длины сторон треугольника")
        description("Проверка длин катетов и гипотенузы")

        polygon.lines.forEach { it.autoCalculateLength() }
        ok("Вычислены дилны сторон")

        assert(polygon.lines[0].length == LEG_SIZE)
        ok("Первый катет корректен")

        assert(polygon.lines[1].length == sqrt(LEG_SIZE.pow(2) * 2))
        ok("Гипотенуза корректна")

        assert(polygon.lines[2].length == LEG_SIZE)
        ok("Второй катет корректен")
    }
}
