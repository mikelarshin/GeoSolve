package open.geosolve.geosolve.view.views.canvas

import open.geosolve.geosolve.model.data.*


private interface ElementList {
    fun update()
}

class NodeSet(val figureList: List<Figure>) : LinkedHashSet<Node>(), ElementList {

    override fun update() {
        this.clear()
        this.addAll(figureList.flatMap { it.mNodes })
    }

    override fun remove(node: Node): Boolean {
        for (figure in figureList)
            if (node in figure.mNodes)
                figure.mNodes.remove(node)
        return true
    }
}

class LineList(val figureList: List<Figure>) : HashSet<Line>(), ElementList {

    override fun update() {
        this.clear()
        this.addAll(figureList.flatMap { it.mLines })
    }

    override fun remove(line: Line): Boolean {
        for (figure in figureList)
            if (line in figure.mLines)
                figure.mLines.remove(line)
        return true
    }
}

class AngleList(val figureList: List<Figure>) : HashSet<Angle>(), ElementList {

    override fun update() {
        this.clear()
        this.addAll(figureList.flatMap { it.mAngles })
    }

    override fun remove(angle: Angle): Boolean {
        for (figure in figureList)
            if (angle in figure.mAngles)
                figure.mAngles.remove(angle)
        return true
    }
}

class CircleList(val figureList: List<Figure>) : HashSet<Circle>(), ElementList {

    override fun update() {
        this.clear()
        this.addAll(figureList.flatMap { listOf(it.mCircle) }.filterNotNull())
    }

    override fun remove(circle: Circle): Boolean {
        for (figure in figureList)
            if (circle == figure.mCircle)
                figure.mCircle = null
        return true
    }
}