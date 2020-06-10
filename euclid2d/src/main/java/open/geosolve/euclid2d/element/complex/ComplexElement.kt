package open.geosolve.euclid2d.element.complex

// TODO(CODE) isMatch
abstract class ComplexElement {

    val describedAround = mutableListOf<ComplexElement>()
    val inscribedInto = mutableListOf<ComplexElement>()

    abstract fun isDescribedAround(element: ComplexElement): Boolean

    // TODO(ARCH) Create overload function for PrimitiveElement
    abstract fun isInscribedInto(element: ComplexElement): Boolean
}