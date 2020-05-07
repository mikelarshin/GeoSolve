package open.geosolve.geosolve.model

import open.geosolve.geosolve.model.data.*

class FigureController(val figure: Figure) {

    fun addNode(node: Node) {
        figure.mNodes.add(node)
    }

    fun addLine(startNode: Node, finalNode: Node) {
        val line = Line(startNode, finalNode)
        figure.mLines.add(line)

        startNode.finalLine = line
        finalNode.startLine = line
    }

    fun addAngle(startLine: Line, finalLine: Line) {
        val angle = Angle(startLine, finalLine)
        figure.mAngles.add(angle)

        startLine.startNode.startAngle = angle
        startLine.finalNode.centerAngle = angle
        finalLine.finalNode.finalAngle = angle
    }

    fun delNode(touchX: Float, touchY: Float) {
        getNode(touchX, touchY)?.let { node ->
            for (element in node.getConnectionList()) {
                if (figure.find == element)
                    figure.find = null
                element?.delConnection()
                figure.mAngles.remove(element)
                figure.mLines.remove(element)
            }

            figure.mNodes.remove(node)
        }
    }

    fun clearFigure() {
        figure.mNodes.clear()
        figure.mLines.clear()
        figure.mAngles.clear()
        figure.find = null
    }

    fun getNode(touchX: Float, touchY: Float): Node? {
        var outNode: Node? = null
        for (node in figure.mNodes)
            if (node.inRadius(touchX, touchY)) {
                outNode = node
                break
            }
        return outNode
    }

    private fun getLine(touchX: Float, touchY: Float): Line? {
        var outLine: Line? = null
        for (line in figure.mLines)
            if (line.inRadius(touchX, touchY)) {
                outLine = line
                break
            }
        return outLine
    }

    fun getElement(x: Float, y: Float, unSelectableCallback: () -> Unit): Element? {

        getNode(x, y)?.let { node ->
            if (node.centerAngle != null)
                return node.centerAngle
            else
                unSelectableCallback()
        }

        getLine(x, y)?.let { line ->
            return line
        }

        return null
    }

    fun closeFigureInStartPoint(){
        val closeNode = figure.mNodes[0]
        addLine(figure.mNodes.last(), closeNode)
        addAngle(closeNode.startLine?.startNode?.startLine!!, closeNode.startLine!!)
        addAngle(closeNode.startLine!!, closeNode.finalLine!!)
    }
}