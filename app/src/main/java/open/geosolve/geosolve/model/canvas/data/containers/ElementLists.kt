package open.geosolve.geosolve.model.canvas.data.containers

import open.geosolve.geosolve.model.canvas.data.elements.Angle
import open.geosolve.geosolve.model.canvas.data.elements.Circle
import open.geosolve.geosolve.model.canvas.data.elements.Line
import open.geosolve.geosolve.model.canvas.data.elements.Node

private interface ElementSet {
    fun update()
}

class FigureSet : LinkedHashSet<Figure>() {
    init {
        this.add(Figure())
    }

    override fun clear() {
        super.clear()
        this.add(Figure())
    }

    fun nextFigure() {
        this.add(Figure())
    }
}

class NodeSet(private val figureSet: FigureSet) : LinkedHashSet<Node>(), ElementSet {

    override fun update() {
        this.clear()
        this.addAll(figureSet.flatMap { it.mNodes })
    }

    override fun remove(node: Node): Boolean {
        for (figure in figureSet)
            if (node in figure.mNodes)
                figure.mNodes.remove(node)
        return true
    }
}

class LineSet(private val figureSet: FigureSet) : HashSet<Line>(), ElementSet {

    override fun update() {
        this.clear()
        this.addAll(figureSet.flatMap { it.mLines })
    }

    override fun remove(line: Line): Boolean {
        for (figure in figureSet)
            if (line in figure.mLines)
                figure.mLines.remove(line)
        return true
    }
}

class AngleSet(private val figureSet: FigureSet) : HashSet<Angle>(), ElementSet {

    override fun update() {
        this.clear()
        this.addAll(figureSet.flatMap { it.mAngles })
    }

    override fun remove(angle: Angle): Boolean {
        for (figure in figureSet)
            if (angle in figure.mAngles)
                figure.mAngles.remove(angle)
        return true
    }
}

class CircleSet(private val figureSet: FigureSet) : HashSet<Circle>(), ElementSet {

    override fun update() {
        this.clear()
        this.addAll(figureSet.flatMap { listOf(it.mCircle) }.filterNotNull())
    }

    override fun remove(circle: Circle): Boolean {
        for (figure in figureSet)
            if (circle == figure.mCircle)
                figure.mCircle = null
        return true
    }
}