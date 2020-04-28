package open.geosolve.geosolve.repository.model

abstract class Element {
    private var value: LinkFloat = LinkFloat()

    var onKnownFun: (thisElement: Element) -> Unit = {}

    private var whereFromValueList: List<Element>? = null

    // setDraw - use in work with canvas
    // setGraph - use in work with solve
    fun setValueDraw(value: Float) {
        this.value.float = value
    }

    fun setDependentValueDraw(value: LinkFloat) {
        this.value = value
    }

    fun setValueGraph(value: Float?, whereFromValueList: List<Element>) {
        this.value.float = value
        this.whereFromValueList = whereFromValueList
    }

    // Использовать если хочешь чтобы длина линии или градуса угла зависела от переданого LinkFloat
    fun setDependentValueGraph(value: LinkFloat, whereFromValueList: List<Element>) {
        this.value = value
        this.whereFromValueList = whereFromValueList
        onKnownFun(this)
    }

    fun getLinkValue() = value

    fun getValue() = value.float

    fun solve() {
        if (value.float != null)
            onKnownFun(this)
    }
}