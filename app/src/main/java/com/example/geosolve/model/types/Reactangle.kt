package com.example.geosolve.model.types

import com.example.geosolve.model.Solve
import com.example.geosolve.model.StepSlove
import com.example.geosolve.model.figure.Angle
import com.example.geosolve.model.figure.Figure

object Reactangle : TypeFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 4 && figure.mLines.size == 4
//        return figure.mNodes.size == 4 && figure.mAngles.filter { (it.value == 90f) or (it.value == null) }.size == 4
    }

    override fun solve(figure: Figure) {
        ruleFirst(figure)
        ruleSecond(figure)
    }

    fun ruleFirst(figure: Figure) {
        val line1 = figure.mLines[0].length == null
        val line2 = figure.mLines[1].length == null
        val line3 = figure.mLines[2].length == null
        val line4 = figure.mLines[3].length == null
        val templateVerbal = "Присваиваем длине стороны %s длину стороны %s"
        val templateExpression = "%s = %s = %s"

        if (line1 && !line3) {
            figure.mLines[0].length = figure.mLines[2].length
            Solve.stepList.add(
                StepSlove(
                    templateVerbal,
                    templateExpression,
                    mutableListOf(figure.mLines[0], figure.mLines[2]),
                    "AB", "CD", "AB", "CD",
                    figure.mLines[2].length.toString()
                )
            )
        }
        if (line3 && !line1) {
            figure.mLines[2].length = figure.mLines[0].length
            Solve.stepList.add(
                StepSlove(
                    templateVerbal,
                    templateExpression,
                    mutableListOf(figure.mLines[2], figure.mLines[0]),
                    "CD", "AB", "CD", "AB",
                    figure.mLines[0].length.toString()
                )
            )
        }
        if (line2 && !line4) {
            figure.mLines[1].length = figure.mLines[3].length
            Solve.stepList.add(
                StepSlove(
                    templateVerbal,
                    templateExpression,
                    mutableListOf(figure.mLines[1], figure.mLines[3]),
                    "BC", "DA", "BC", "DA",
                    figure.mLines[3].length.toString()
                )
            )
        }
        if (line4 && !line2) {
            figure.mLines[3].length = figure.mLines[1].length
            Solve.stepList.add(
                StepSlove(
                    templateVerbal,
                    templateExpression,
                    mutableListOf(figure.mLines[3], figure.mLines[1]),
                    "DA", "BC", "DA", "BC",
                    figure.mLines[1].length.toString()
                )
            )
        }
    }


    fun ruleSecond(figure: Figure) {
        if (figure.mAngles.filter { it.value == 90f }.size != 4) {
            for (angle: Angle in figure.mAngles)
                angle.value = 90f
            Solve.stepList.add(StepSlove(
                "У прямоугольника все углы равны 90°",
                "AB = BC = CD = DA = 90°",
            figure.mAngles))
        }
    }
}

