package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.controllers.AllCircles
import open.geosolve.geosolve.model.canvas.controllers.AllNodes
import open.geosolve.geosolve.model.canvas.controllers.ElementGetter.getElement
import open.geosolve.geosolve.model.canvas.controllers.Figure
import open.geosolve.geosolve.model.canvas.controllers.FigureList
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Node
import open.geosolve.geosolve.model.canvas.data.generalized.Element
import open.geosolve.geosolve.model.canvas.math.XYPoint

abstract class BaseTool : Tool { // BaseTool реализует передвижение элементов, выделение их

    companion object {
        var moveQuantity = 0
        val movementWasNot: Boolean // нужно чтобы совершал действие совершалось только при чётком нажатии
            get() = moveQuantity < 5
        var selectElement: Element? = null
    }

    override fun onTouchDown(point: XYPoint) {
        getElement(point)?.let { element ->
            selectElement = element
        }
    }

    override fun onTouchMove(point: XYPoint) {
        when (selectElement) {
            is Circle -> (selectElement as Circle).moveRadius(point)
            is Node -> (selectElement as Node).move(point)
        }

        moveQuantity++
    }

    override fun onTouchUp(point: XYPoint) {
        if (movementWasNot) { onTouchElement(point) }

        moveQuantity = 0
        selectElement = null

        setNodeChars()

        if (Figure.isComplete())
            FigureList.nextFigure() // переход на следующую фигуру
    }

    open fun onTouchElement(point: XYPoint) {}

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