package open.geosolve.geosolve.model.data.generalized

import open.geosolve.geosolve.view.rules.Rule

abstract class SolveGraph {

    private var value: Float? = null
    private var dependence: (Float?) -> Float? = {value -> value}

    var onKnownFunctions: MutableSet<(knownGraph: SolveGraph) -> Unit> = mutableSetOf()

    var whereFromValueList: List<SolveGraph>? = null
        private set

    lateinit var rule: Rule

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

    fun setValueGraph(value: Float, whereFromValueList: List<SolveGraph>, rule: Rule) {
        this.value = value
        setValueGraph(whereFromValueList, rule)
    }

    fun setDependentValueGraph(dependence: (Float?) -> Float?, whereFromValueList: List<SolveGraph>, rule: Rule) {
        this.dependence = dependence
        setValueGraph(whereFromValueList, rule)
    }

    private fun setValueGraph(whereFromValueList: List<SolveGraph>, rule: Rule){
        this.whereFromValueList = whereFromValueList
        this.rule = rule

        for (onKnownFun in onKnownFunctions)
            onKnownFun(this)
    }

    fun getValue() = value ?: dependence(value)

    fun solve() {
        if (getValue() != null)
            for (onKnownFun in onKnownFunctions)
                onKnownFun(this)
    }
}