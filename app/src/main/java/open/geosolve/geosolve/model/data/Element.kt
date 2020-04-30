package open.geosolve.geosolve.model.data

abstract class Element {
    private var value: LinkFloat = LinkFloat()

    lateinit var verbal: CharSequence
        private set
    lateinit var expression: CharSequence
        private set

    var onKnownFun: (thisElement: Element) -> Unit = {}

    var whereFromValueList: List<Element>? = null
        private set

    // setDraw - use it in work with canvas
    // setGraph - use it in work with solve
    // setDependent - use it if you want to set a value dependency
    // set - use it if you want to simple set a value
    fun setValueDraw(value: Float) {
        this.value.float = value
    }

    fun setDependentValueDraw(value: LinkFloat) {
        this.value = value
    }

    fun setValueGraph(value: Float?, whereFromValueList: List<Element>,
                      verbal: CharSequence, expression: CharSequence) {
        this.value.float = value
        setValue(whereFromValueList,verbal, expression)
    }

    fun setDependentValueGraph(value: LinkFloat, whereFromValueList: List<Element>,
                               verbal: CharSequence, expression: CharSequence) {
        this.value = value
        setValue(whereFromValueList,verbal, expression)
    }

    private fun setValue(whereFromValueList: List<Element>,verbal: CharSequence, expression: CharSequence){
        this.whereFromValueList = whereFromValueList
        this.verbal = verbal
        this.expression = expression
        onKnownFun(this)
    }

    fun getLinkValue() = value

    fun getValue() = value.float

    fun solve() {
        if (value.float != null)
            onKnownFun(this)
    }

    fun zeroing(){
        onKnownFun = {}
        verbal = "Если ты видишь это то в системе произошла ошибка, не все графы заполнены"
        expression = "Если ты видишь это то в системе произошла ошибка, не все графы заполнены"
    }
}