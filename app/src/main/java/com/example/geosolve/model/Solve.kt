package com.example.geosolve.model

import com.example.geosolve.model.figure.Angle
import com.example.geosolve.model.figure.Figure
import com.example.geosolve.model.figure.Line
import com.example.geosolve.model.types.Reactangle
import com.example.geosolve.model.types.Triangle
import com.example.geosolve.model.types.TypeFigure
import com.example.geosolve.view.RecycleAdapter

class Solve(val figure: Figure) : Thread() {
    override fun run() {
        RecycleAdapter.addAll(solve())
    }

    companion object {
        val stepList: MutableList<StepSlove> = ArrayList()
    }
    private var type: TypeFigure? = when {
        Reactangle.isMatch(figure) -> Reactangle
        Triangle.isMatch(figure) -> Triangle
        // example (X : TypeFigure).isMatch(figure) -> X
        else -> null
    }

    private fun solve(step: Int = 0): MutableList<StepSlove> {
        if ((type == null) or (step > 30))
            return mutableListOf(StepSlove(
                "",
                "Мы не знаем как решить эту задачу",
                null))
        type!!.solve(figure)
        if (!when (figure.find) {
                is Line -> (figure.find as Line).length != null
                is Angle -> (figure.find as Angle).value != null
                else -> false
            }
        )
            return solve(step+1)
        return stepList
    }
}