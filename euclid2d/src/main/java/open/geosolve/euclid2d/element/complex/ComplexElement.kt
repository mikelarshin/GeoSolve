package open.geosolve.euclid2d.element.complex

abstract class ComplexElement {

    val described = mutableListOf<ComplexElement>()
    val inscribed = mutableListOf<ComplexElement>()

    abstract fun isDescribedAround(element: ComplexElement): Boolean
    abstract fun isInscribedInto(element: ComplexElement): Boolean
}