package com.example.geosolve.model

import android.util.Log
import com.example.geosolve.status.Mode
import com.example.geosolve.status.State
import java.util.*

class Figure {

    private val lines: ArrayList<Line> = ArrayList()
    val nodes: ArrayList<Node> = ArrayList()
    var mode: Mode = Mode.ADDMOVE
    private var state: State = State.DID
    private var numOfCall: Int = 0

    fun setState(touchX: Float, touchY: Float) {
        for (node in nodes) {
            if (node.inRadius(touchX, touchY)) {
                node.isMove = true
                state = State.ONPOINT
                break
            }
        }
    }

    fun moveNode(touchX: Float, touchY: Float) {
        for (node in nodes) {
            if (node.isMove) {
                node.moveNode(touchX, touchY)
            }
        }
        numOfCall++
    }

    fun addNodeIf(touchX: Float, touchY: Float) {
        when (mode) {
            Mode.ADDMOVE -> {
                if (state == State.DID)
                    addNode(Node(touchX, touchY))
                else if ((state == State.ONPOINT) and (numOfCall < 2)) {
                    Log.e("tag", "finish the figure")               // finish the figure!!!
                }
            }
            Mode.DELMOVE -> delNode(touchX, touchY)
        }

        numOfCall = 0
        state = State.DID
        stopAllNode()
    }

    private fun stopAllNode(): Boolean {
        var answer = false
        for (node in nodes) {
            answer = answer or node.stopMove()
        }
        return answer
    }

    private fun addNode(node: Node) {
        nodes.add(node)
    }

    private fun delNode(touchX: Float, touchY: Float) {
        for (node in nodes)
            if (node.inRadius(touchX, touchY)) {
                val index: Int = nodes.indexOf(node)
                nodes.removeAt(index)
                break
            }

    }
}