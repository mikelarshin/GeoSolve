package open.geosolve.geosolve.model.data

abstract class Element {
    private var value: LinkFloat = LinkFloat()

    lateinit var formula: CharSequence
        private set
    lateinit var verbal: CharSequence
        private set
    lateinit var expression: () -> CharSequence
        private set

    var onKnownFunList: MutableList<(thisElement: Element) -> Unit> = mutableListOf()
    private var dependence: (Float?) -> Float? = {value -> value}

    var whereFromValueList: List<Element>? = null
        private set

    // setDraw - use it in work with canvas
    // setGraph - use it in work with solve
    // setDependent - use it if you want to set a value dependency
    // set - use it if you want to simple set a value
    fun setValueDraw(value: Float) {
        this.value.float = value
    }

    fun setDependentValueDraw(dependence: (Float?) -> Float?) {
        this.dependence = dependence
    }

    fun setValueGraph(value: Float, whereFromValueList: List<Element>,
                      verbal: CharSequence, formula: CharSequence, expression: () -> CharSequence) {
        this.value.float = value
        setValue(whereFromValueList, verbal, formula, expression)
    }

    fun setDependentValueGraph(dependence: (Float?) -> Float?, whereFromValueList: List<Element>,
                               verbal: CharSequence, formula: CharSequence, expression: () -> CharSequence) {
        this.dependence = dependence
        setValue(whereFromValueList,verbal, formula, expression)
    }

    private fun setValue(whereFromValueList: List<Element>,
                         verbal: CharSequence,
                         formula: CharSequence,
                         expression: () -> CharSequence){
        this.whereFromValueList = whereFromValueList
        this.formula = formula
        this.verbal = verbal
        this.expression = expression
        for (onKnownFun in onKnownFunList)
            onKnownFun(this)
    }

    fun getLinkValue() = value

    fun getValue() = value.float ?: dependence(value.float)

    fun solve() {
        if (getValue() != null)
            for (onKnownFun in onKnownFunList)
                onKnownFun(this)
    }
}