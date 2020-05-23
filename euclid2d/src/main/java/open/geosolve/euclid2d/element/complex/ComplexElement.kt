package open.geosolve.euclid2d.element.complex

abstract class ComplexElement {
    val described = mutableListOf<ComplexElement>()
    val inscribed = mutableListOf<ComplexElement>()
}