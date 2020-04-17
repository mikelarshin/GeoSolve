package com.example.geosolve.model.figure

import kotlin.math.hypot
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class Line(val startNode: Node, val finNode: Node) {
    var length: Float? = null

    init {
        if (startNode == finNode)
            throw Exception("Line constructor get the same Node")
        startNode.neighborLines.add(this)
        finNode.neighborLines.add(this)
    }

    fun inRadius(x: Float, y: Float): Boolean {
        val distanceStart: Float = hypot(x - startNode.x, y - startNode.y)
        val distanceFin: Float = hypot(x - finNode.x, y - finNode.y)
        val lineLength: Float = hypot(startNode.x - finNode.x, startNode.y - finNode.y)

        val per: Float = (distanceStart + distanceFin + lineLength) / 2


        val distance: Float = sqrt(per * (per - distanceStart) *
                (per - distanceFin) *
                (per - lineLength)) / lineLength

        return distance < 25
    }

}