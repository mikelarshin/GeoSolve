package open.geosolve.geosolve

import open.geosolve.geosolve.GlobalFiguresController.figureList
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node

val AllNodes
    get() = NodeSet.update()
val AllLines
    get() = LineList.update()
val AllAngles
    get() = AngleList.update()
val AllCircles: CircleList
    get() = CircleList.update()


private interface ElementList {
    fun update(): ElementList
}

object NodeSet : LinkedHashSet<Node>(), ElementList {

    override fun update(): NodeSet {
        this.clear()
        this.addAll(figureList.flatMap { it.mNodes })
        return this
    }

    override fun remove(node: Node): Boolean {
        for (figure in figureList)
            if (node in figure.mNodes)
                figure.mNodes.remove(node)
        return true
    }
}

object LineList : HashSet<Line>(), ElementList {

    override fun update(): LineList {
        this.clear()
        this.addAll(figureList.flatMap { it.mLines })
        return this
    }

    override fun remove(line: Line): Boolean {
        for (figure in figureList)
            if (line in figure.mLines)
                figure.mLines.remove(line)
        return true
    }
}

object AngleList : HashSet<Angle>(), ElementList {

    override fun update(): AngleList {
        this.clear()
        this.addAll(figureList.flatMap { it.mAngles })
        return this
    }

    override fun remove(angle: Angle): Boolean {
        for (figure in figureList)
            if (angle in figure.mAngles)
                figure.mAngles.remove(angle)
        return true
    }
}

object CircleList : HashSet<Circle>(), ElementList {

    override fun update(): CircleList {
        this.clear()
        this.addAll(figureList.flatMap { listOf(it.mCircle) }.filterNotNull())
        return this
    }

    override fun remove(circle: Circle): Boolean {
        for (figure in figureList)
            if (circle == figure.mCircle)
                figure.mCircle = null
        return true
    }
}