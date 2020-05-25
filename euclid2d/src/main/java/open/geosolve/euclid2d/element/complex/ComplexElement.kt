package open.geosolve.euclid2d.element.complex

abstract class ComplexElement {

    val describedAround = mutableListOf<ComplexElement>()
    val inscribedInto = mutableListOf<ComplexElement>()

    abstract fun isDescribedAround(element: ComplexElement): Boolean
    abstract fun isInscribedInto(element: ComplexElement): Boolean
}