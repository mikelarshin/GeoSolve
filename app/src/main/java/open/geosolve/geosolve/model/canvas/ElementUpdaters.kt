package open.geosolve.geosolve.model.canvas

import open.geosolve.geosolve.model.canvas.FigureController.addAngle
import open.geosolve.geosolve.model.canvas.FigureController.addLine
import open.geosolve.geosolve.model.canvas.FigureController.figure
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.Angle.Companion.isCorrectNodes

object ElementUpdaters {
    fun updateNodes() {
        figure.mNodes.addAll(figure.mLines.flatMap { it.nodes }) // mNodes это Set так что повторок не будет
        figure.mNodes.addAll(figure.mAngles.flatMap { it.nodes })
    }

    fun updateLines() {
        val lineList = AllLines - figure.mLines // все линии кроме тех что в нашей фигуре
        for (line in lineList)
            if (figure.contains(line.firstNode) &&
                figure.contains(line.secondNode)
            )
                addLine(line)
    }

    fun updateAngles() {
        for (startLine in figure.mLines) // TODO(сделай по всем линиям когда перепишешь isClose)
            for (finalLine in figure.mLines)
                if (startLine != finalLine && isCorrectNodes(startLine, finalLine) && !AllAngles.any { it.equal(startLine, finalLine) })
                    addAngle(Angle(startLine, finalLine))
    }
}