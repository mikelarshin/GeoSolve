package open.geosolve.geosolve.model.tools

import open.geosolve.geosolve.AllAngles
import open.geosolve.geosolve.AllLines
import open.geosolve.geosolve.GlobalFiguresController
import open.geosolve.geosolve.model.FigureController
import open.geosolve.geosolve.model.FigureController.addCircle
import open.geosolve.geosolve.model.data.*
import open.geosolve.geosolve.model.data.generalized.Bind

object AddTool : BaseTool() {
    var lastNode: Node? = null

    override fun onTouchMove(x: Float, y: Float) {
        super.onTouchMove(x, y)

        if (selectElement == null && moveQuantity == 6) {
            if (FigureController.figure.isNotEmpty())
                GlobalFiguresController.figureList.add(Figure()) // переходим на следующую фигуру

            val circle = Circle(Node(x, y))
            addCircle(circle)
            selectElement = circle
        }
    }

    override fun onTouchUp(x: Float, y: Float) {
        if (movementWasNot) {
            when (selectElement) {
                is Node -> onTouchPoint(selectElement as Node)
                is Line -> onTouchLine(selectElement as Line, x, y)
                is Circle -> onTouchCircleLine(selectElement as Circle, x, y)
                null -> onTouchCanvas(x, y)
            }
        }

        if (FigureController.figure.isClose())
            lastNode = null

        super.onTouchUp(x, y)
    }





    private fun onTouchPoint(touchNode: Node) {
        if (touchNode != lastNode && lastNode != null) {  // если стартовая и конечная точка линии не равны и прошлая точка есть

            val newLine = Line(lastNode!!, touchNode)
            if (!equalsContainsLine(newLine)) {
                FigureController.addLine(newLine)

                if (!FigureController.figure.mNodes.contains(lastNode!!))
                    FigureController.figure.mNodes.add(lastNode!!)
                if (!FigureController.figure.mNodes.contains(touchNode))
                    FigureController.figure.mNodes.add(touchNode)

                updateLines()
                updateAngles() // FIXME(updateAngles)
            }
        }
        lastNode = touchNode
    }

    private fun onTouch(touchX: Float, touchY: Float, bind: Bind? = null) {
        val newNode = Node(touchX, touchY)
        newNode.bind = bind

        FigureController.addNode(newNode)

        lastNode?.let {
            val newLine = Line(lastNode!!, newNode)
            FigureController.addLine(newLine)

            if (!FigureController.figure.mNodes.contains(lastNode!!))
                FigureController.figure.mNodes.add(lastNode!!)

            updateLines()
            updateAngles() // FIXME(updateAngles)
        }

        lastNode = newNode
    }

    private fun onTouchCanvas(touchX: Float, touchY: Float) {
        onTouch(touchX, touchY)
    }

    private fun onTouchLine(line: Line, x: Float, y: Float) {
        onTouch(x, y, line)
    }

    private fun onTouchCircleLine(circle: Circle, x: Float, y: Float) {
        onTouch(x, y, circle)
    }

    private fun updateLines() {
        val lineList = AllLines - FigureController.figure.mLines
        for (line in lineList)
            if (FigureController.figure.mNodes.contains(line.startNode) && FigureController.figure.mNodes.contains(
                    line.finalNode
                )
            )
                FigureController.addLine(line)
    }

    private fun updateAngles() {
        for (startLine in FigureController.figure.mLines)
            for (finalLine in FigureController.figure.mLines)
                if (finalLine != startLine && startLine.finalNode == finalLine.startNode) {
                    val newAngle = Angle(startLine, finalLine)
                    if (!equalsContainsAngle(newAngle))
                        FigureController.addAngle(newAngle)
                }

        // Простой перебор по всем вариантам углов которые есть в фигуре
        // Это система работает не всегда и в будующем принесёт много бед
        // Нужно её переписать FIXME(updateAngles)
    }

    private fun equalsContainsAngle(newAngle: Angle): Boolean {
        for (angle in AllAngles)
            if (angle.startNode == newAngle.startNode &&
                angle.finalNode == newAngle.finalNode &&
                angle.angleNode == newAngle.angleNode
            )
                return true
        return false
    }

    private fun equalsContainsLine(newLine: Line): Boolean {
        for (line in AllLines)
            if ((line.startNode == newLine.startNode && line.finalNode == newLine.finalNode) ||
                (line.startNode == newLine.finalNode && line.finalNode == newLine.startNode)
            )
                return true
        return false
    }
}