package open.geosolve.euclid2d.element

import open.geosolve.euclid2d.element.complex.ComplexElement

class Plane {

    private val _elements = mutableListOf<ComplexElement>()
    val elements: List<ComplexElement> get() = _elements

    fun append(element: ComplexElement) {
        _elements.forEach {
            if (it.isDescribedAround(element)) {
                it.inscribedInto += element
                element.describedAround += it
            }

            if (it.isInscribedInto(element)) {
                it.describedAround += element
                element.inscribedInto += it
            }
        }

        _elements += element
    }
}