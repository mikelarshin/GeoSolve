package open.geosolve.geosolve.model.canvas.controllers

import open.geosolve.geosolve.model.canvas.data.containers.CanvasData

lateinit var canvasData: CanvasData

var find
    get() = canvasData.find
    set(value) { canvasData.find = value }

val FigureList get() = canvasData.figureList
val Figure get() = FigureList.last()

val AllNodes get() = canvasData.allNodes
val AllLines get() = canvasData.allLines
val AllAngles get() = canvasData.allAngles
val AllCircles get() = canvasData.allCircles