package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.AllCircles
import open.geosolve.geosolve.model.canvas.AllNodes
import open.geosolve.geosolve.model.canvas.ElementGetter.getElement
import open.geosolve.geosolve.model.canvas.FigureController.figure
import open.geosolve.geosolve.model.canvas.FigureList
import open.geosolve.geosolve.model.canvas.data.Circle
import open.geosolve.geosolve.model.canvas.data.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Element

abstract class BaseTool : Tool {

    companion object {
        var moveQuantity = 0
        val movementWasNot: Boolean // нужно чтобы совершал действие совершалось только при чётком нажатии
            get() = moveQuantity < 5
        var selectElement: Element? = null
    }

    override fun onTouchDown(x: Float, y: Float) {
        getElement(x, y)?.let { element ->
            selectElement = element
        }
    }

    override fun onTouchMove(x: Float, y: Float) {
        when (selectElement) {
            is Circle -> (selectElement as Circle).moveRadius(x, y)
            is Node -> (selectElement as Node).move(x, y)
        }

        moveQuantity++
    }

    override fun onTouchUp(x: Float, y: Float) {
        moveQuantity = 0
        selectElement = null

        setNodeChars()

        if (figure.isComplete())
            FigureList.nextFigure() // переход на следующую фигуру
    }

    private fun setNodeChars() {
        val circleNodeList = AllCircles.map { it.centerNode }
        circleNodeList.forEach { it.char = "O" }

        val alphabet1 = listOf("") + (('A'..'Z').toList())
        val alphabet2 = ('A'..'Z').toList()

        val nodes = AllNodes.filter { it !in circleNodeList }

        for (index in nodes.indices)
            nodes[index].char = "${alphabet1[index / 26]}${alphabet2[index % 26]}"
    }
}