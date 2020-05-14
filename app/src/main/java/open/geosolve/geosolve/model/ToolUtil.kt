package open.geosolve.geosolve.model

import open.geosolve.geosolve.App.Companion.allNodes

object ToolUtil {
    fun setNodeChars() {
        val charRange = ('A'..'Z').toList()
        for (i in 0 until allNodes.size)
            allNodes[i].char = charRange[i]
    }
}