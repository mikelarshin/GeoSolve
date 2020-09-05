package open.geosolve.geosolve.model.solve

import open.geosolve.geosolve.model.canvas.data.Figure

interface SolveFigure {
    fun isMatch(figure: Figure): Boolean
    fun setGraphs(figure: Figure) {}
    fun setSubType(figure: Figure) {}
//    fun collectData(figure: Figure): Boolean // TODO(implement)
//    fun isCorrectData(figure: Figure): Boolean // TODO(implement)

    // TODO(создать функцию которая берёт следующий по списку элемент типа list[(i + 1) % 2])
}
