package open.geosolve.geosolve.repository.model

class Node(
    var x: Float,
    var y: Float
) : Element {

    var isMove = false
    val neighborLines: MutableList<Line> = ArrayList()
    val neighborAngles: MutableList<Angle> = ArrayList()

    fun moveNode(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun inRadius(x: Float, y: Float): Boolean {
        val xBool = this.x - 25 < x && x < this.x + 25

        val yBool = this.y - 25 < y && y < this.y + 25

        return xBool && yBool
    }

    // TODO Rewrite this
    fun stopMove(): Boolean {
        return if (isMove) {
            isMove = false
            true
        } else false
    }

}