package open.geosolve.euclid2d.helper

data class LimitFloat(
    private var value: Float,
    val maxValue: Float,
    val minValue: Float
) {

    fun getValue(): Float = value

    fun setValue(value: Float) {
        check(value in minValue..maxValue) { "Value not in range" }
        this.value = value
    }
}