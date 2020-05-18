package open.geosolve.geosolve.model.data.generalized

abstract class SolveGraph {

    private var value: Float? = null
    private var dependence: (Float?) -> Float? = {value -> value}

    var onKnownFunList: MutableList<(knownGraph: SolveGraph) -> Unit> = mutableListOf()

    var whereFromValueList: List<SolveGraph>? = null
        private set

    lateinit var getFormula: () -> CharSequence
        private set
    lateinit var getVerbal: () -> CharSequence
        private set
    lateinit var getExpression: () -> CharSequence
        private set

    // setDraw - use it in work with canvas
    // setGraph - use it in work with solve
    // setDependent - use it if you want to set a value dependency
    // set - use it if you want to simple set a value
    fun setValueDraw(value: Float) {
        this.value = value
    }

    fun setDependentValueDraw(dependence: (Float?) -> Float?) {
        this.dependence = dependence
    }

    fun setValueGraph(value: Float, whereFromValueList: List<SolveGraph>,
                      args: Array<out () -> CharSequence> ) {
        this.value = value
        setValueGraph(whereFromValueList, args)
    }

    fun setDependentValueGraph(dependence: (Float?) -> Float?, whereFromValueList: List<SolveGraph>,
                               args: Array<out () -> CharSequence>) {
        this.dependence = dependence
        setValueGraph(whereFromValueList, args)
    }

    private fun setValueGraph(whereFromValueList: List<SolveGraph>,
                              args: Array<out () -> CharSequence>){
        this.whereFromValueList = whereFromValueList
        this.getFormula = args[0]
        this.getVerbal = args[1]
        this.getExpression = args[2]
        for (onKnownFun in onKnownFunList)
            onKnownFun(this)
    }

    fun getValue() = value ?: dependence(value)

    fun solve() {
        if (getValue() != null)
            for (onKnownFun in onKnownFunList)
                onKnownFun(this)
    }
}