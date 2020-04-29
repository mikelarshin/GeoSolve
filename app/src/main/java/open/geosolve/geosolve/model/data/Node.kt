package open.geosolve.geosolve.model.data

class Node(var x: Float, var y: Float) {

    var startLine: Line? = null
    var finalLine: Line? = null
    var startAngle: Angle? = null
    var centerAngle: Angle? = null
    var finalAngle: Angle? = null

    fun delConnection(){
        startLine?.delConnection()
        finalLine?.delConnection()
        startAngle?.delConnection()
        centerAngle?.delConnection()
        finalAngle?.delConnection()
    }

    fun moveNode(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun inRadius(x: Float, y: Float): Boolean {
        val xBool = this.x - 25 < x && x < this.x + 25

        val yBool = this.y - 25 < y && y < this.y + 25

        return xBool && yBool
    }
}