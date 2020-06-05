package open.geosolve.geosolve

import open.geosolve.geosolve.GlobalFiguresController.figureList
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Circle
import open.geosolve.geosolve.model.data.Line
import open.geosolve.geosolve.model.data.Node
import org.w3c.dom.Element

val AllNodes
    get() = object : ArrayList<Node>() {
        init {
            this.addAll(figureList.flatMap { it.mNodes })
        }

        override fun remove(node: Node): Boolean {
            for (figure in figureList)
                if (node in figure.mNodes)
                    figure.mNodes.remove(node)
            return true
        }
    }

val AllLines
    get() = object : ArrayList<Line>() {
        init {
            this.addAll(figureList.flatMap { it.mLines })
        }

        override fun remove(line: Line): Boolean {
            for (figure in figureList)
                if (line in figure.mLines)
                    figure.mLines.remove(line)
            return true
        }
    }

val AllAngles
    get() = object : ArrayList<Angle>() {
        init {
            this.addAll(figureList.flatMap { it.mAngles })
        }

        override fun remove(angle: Angle): Boolean {
            for (figure in figureList)
                if (angle in figure.mAngles)
                    figure.mAngles.remove(angle)
            return true
        }
    }

val AllCircles
    get() = object : ArrayList<Circle>() {
        init {
            this.addAll(figureList.flatMap { listOf(it.mCircle) }.filterNotNull())
        }

        override fun remove(circle: Circle): Boolean {
            for (figure in figureList)
                if (circle == figure.mCircle)
                    figure.mCircle = null
            return true
        }
    }