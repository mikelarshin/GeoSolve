package open.geosolve.geosolve.model.canvas.controllers

import open.geosolve.geosolve.model.canvas.controllers.FigureController.addAngle
import open.geosolve.geosolve.model.canvas.controllers.FigureController.addLine
import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Angle.Companion.isCorrectNodes

object ElementUpdaters {
    fun updateNodes() {
        Figure.mNodes.addAll(Figure.mLines.flatMap { it.nodes }) // mNodes это Set так что повторок не будет
        Figure.mNodes.addAll(Figure.mAngles.flatMap { it.nodes })
    }

    fun updateLines() {
        val lineList = AllLines - Figure.mLines // все линии кроме тех что в нашей фигуре
        for (line in lineList)
            if (Figure.contains(line.firstNode) &&
                Figure.contains(line.secondNode)
            )
                addLine(line)
    }

    fun updateAngles() {
        for (startLine in Figure.mLines) // TODO(сделай по всем линиям когда перепишешь isClose)
            for (finalLine in Figure.mLines)
                if (startLine != finalLine && isCorrectNodes(startLine, finalLine) && !AllAngles.any { it.equal(startLine, finalLine) })
                    addAngle(Angle(startLine, finalLine))
    }
}