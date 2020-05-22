package open.geosolve.geosolve.presentation.canvas.tool

import open.geosolve.geosolve.model.data.Figure
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import open.geosolve.geosolve.model.helper.FigureManipulator

class EraserTool : Tool {

    override fun onTouchCanvas(figure: Figure, x: Float, y: Float) {
        /* Do nothing */
    }

    override fun onTouchNode(figure: Figure, node: Node) {
        FigureManipulator.deleteNode(node)
    }

    override fun onTouchLine(figure: Figure, line: Line) {
        /* TODO(CODE) Нереализовано. Удалять линию вместе с "повисшей" точкой */
    }
}