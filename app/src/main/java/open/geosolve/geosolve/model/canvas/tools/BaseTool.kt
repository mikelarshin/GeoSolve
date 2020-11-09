package open.geosolve.geosolve.model.canvas.tools

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeCanvasData
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeFigure
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.activeFigureList
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.allCircles
import open.geosolve.geosolve.model.canvas.data.containers.CanvasData.Companion.allNodes
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
        activeCanvasData.getElement(point)?.let { element ->
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

        if (activeFigure.isComplete())
            activeFigureList.nextFigure() // переход на следующую фигуру
    }

    open fun onTouchElement(point: XYPoint) {}

    private fun setNodeChars() {
        val circleNodeList = allCircles.map { it.centerNode }
        circleNodeList.forEach { it.char = "O" }

        val alphabet1 = listOf("") + (('A'..'Z').toList())
        val alphabet2 = ('A'..'Z').toList()

        val nodes = allNodes.filter { it !in circleNodeList }

        for (index in nodes.indices)
            nodes[index].char = "${alphabet1[index / 26]}${alphabet2[index % 26]}"
    }
}