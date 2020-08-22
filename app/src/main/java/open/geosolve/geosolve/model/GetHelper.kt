package open.geosolve.geosolve.model

import open.geosolve.geosolve.view.views.canvas.CanvasData

lateinit var canvasData: CanvasData

var find get() = canvasData.find
    set(value) {
        canvasData.find = value
    }

val FigureList get() = canvasData.figureList

val AllNodes get() = canvasData.allNodes
val AllLines get() = canvasData.allLines
val AllAngles get() = canvasData.allAngles
val AllCircles get() = canvasData.allCircles