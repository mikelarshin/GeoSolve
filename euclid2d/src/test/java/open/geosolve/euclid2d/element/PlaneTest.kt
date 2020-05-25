package open.geosolve.euclid2d.element

import open.geosolve.euclid2d.element.complex.Circle
import open.geosolve.euclid2d.element.complex.Polygon
import open.geosolve.euclid2d.helper.TestHelper.description
import open.geosolve.euclid2d.helper.TestHelper.ok
import open.geosolve.euclid2d.helper.TestHelper.test
import open.geosolve.euclid2d.helper.initOnce
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.math.sqrt

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlaneTest {

    companion object {
        private const val SQUARE_SIDE = 4f
    }

    private val plane = Plane()
    private var square by initOnce<Polygon>()

    @Test
    @Order(0)
    fun `Create square`() {

        test("Создание квадрата")
        description("Квадрат со стороной $SQUARE_SIDE созданный около 0")

        square = Polygon().apply {
            appendPoint(-(SQUARE_SIDE / 2), -(SQUARE_SIDE / 2))
            appendPoint(-(SQUARE_SIDE / 2), +(SQUARE_SIDE / 2))
            appendPoint(+(SQUARE_SIDE / 2), +(SQUARE_SIDE / 2))
            appendPoint(+(SQUARE_SIDE / 2), -(SQUARE_SIDE / 2))
            close()
        }

        plane.append(square)

        assert(plane.elements.last() == square)
        ok("Квадрат успешно создан на плоскости")
    }

    @Test
    @Order(1)
    fun `Creating described circle around square`() {

        val r = SQUARE_SIDE / sqrt(2f)

        test("Создание описанной окружности")
        description("Создание описанной окружности вокруг квадрата с радиусом $r")

        // FIXME(CODE) Вычисление радиуса описанной окружности в класс полигона
        val describedCircle =
            Circle(0f, 0f, r)

        assert(describedCircle.isDescribedAround(square))
        ok("Окружность описана около квадрата")

        plane.append(describedCircle)

        assert(plane.elements.last() == describedCircle)
        ok("Окружность добавлена на плоскость")

        assert(square in describedCircle.inscribedInto)
        ok("Квадрат присутствует в списке вписанных в окружность элементов")
    }

    @Test
    @Order(2)
    fun `Creating inscribed circle into square`() {

        val r = SQUARE_SIDE / 2f

        test("Создание вписанной окружности")
        description("Создание вписанной окружности внутри квадрата с радиусом $r")

        // FIXME(CODE) Вычисление радиуса описанной окружности в класс полигона
        val inscribedCircle =
            Circle(0f, 0f, r)

        assert(inscribedCircle.isInscribedInto(square))
        ok("Окружность вписана в квадрат")

        plane.append(inscribedCircle)

        assert(plane.elements.last() == inscribedCircle)
        ok("Окружность добавлена на плоскость")

        assert(square in inscribedCircle.describedAround)
        ok("Квадрат присутствует в списке описанных элементов")
    }
}