package com.example.geosolve.model

import java.util.*

class Figure {

    private val lines: ArrayList<Line> = ArrayList()
    val nodes: ArrayList<Node> = ArrayList()

    fun stopAllNode(): Boolean {
        var answer = false
        for (node in nodes) {
            answer = answer or node.stopMove()
        }
        return answer
    }

    fun addNode(node: Node) {
        nodes.add(node)
    }

    fun delNode(touchX: Float, touchY: Float) {
        for (node in nodes)
            if (node.inRadius(touchX, touchY)) {
                val index: Int = nodes.indexOf(node)
                nodes.removeAt(index)
                break
            }
    }

    fun clearFigure(){
        nodes.clear()
        lines.clear()
    }
}