package open.geosolve.euclid2d.element

import open.geosolve.euclid2d.element.complex.ComplexElement

class Plane {

    private val _elements = mutableListOf<ComplexElement>()
    val elements: List<ComplexElement> get() = _elements

    fun append(element: ComplexElement) {
        _elements += element
    }
}