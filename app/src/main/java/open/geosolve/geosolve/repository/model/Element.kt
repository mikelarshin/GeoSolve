package open.geosolve.geosolve.repository.model

abstract class Element {
    private var value: Float? = null

    var onKnownFun: () -> Unit = {}

    private var whereFromValueList: List<Element>? = null

    fun setValueDraw(value: Float?) {
        this.value = value
    }

    fun setValueGraph(value: Float?, whereFromValueList: List<Element>) {
        onKnownFun()
        this.value = value
        this.whereFromValueList = whereFromValueList
    }

    fun getValue() = value

    fun solve(){
        if (value != null)
            onKnownFun()
    }
}