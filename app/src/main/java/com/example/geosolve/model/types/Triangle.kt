package com.example.geosolve.model.types

import com.example.geosolve.model.Solve
import com.example.geosolve.model.StepSlove
import com.example.geosolve.model.figure.Figure

object Triangle : TypeFigure {
    override fun isMatch(figure: Figure): Boolean {
        return figure.mNodes.size == 3
    }

    override fun solve(figure: Figure) {
        Solve.stepList.add(StepSlove("Это треугольник он не сделан", "x+x+x", null))
    }
}