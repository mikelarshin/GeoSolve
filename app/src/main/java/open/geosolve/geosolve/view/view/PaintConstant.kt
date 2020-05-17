package open.geosolve.geosolve.view.view

import android.content.Context
import android.graphics.Paint
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R

object PaintConstant {
    var mPaintNode:       Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintLine:       Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintCircle:     Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintNodeMark:   Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintLineMark:   Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintMarkCircle: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintText:       Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mPaintAngle:      Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var context: Context? = null
        set(value) {
            field = value!!

            mPaintNode.apply {
                color = context!!.getColor(R.color.color_node) // Not error
                strokeWidth = POINT_SIZE
            }
            mPaintLine.apply {
                color = context!!.getColor(R.color.color_line) // Not error
                strokeWidth = LINE_WIDTH
            }
            mPaintCircle.apply {
                color = context!!.getColor(R.color.color_circle) // Not error
                style = Paint.Style.STROKE
                strokeWidth = LINE_WIDTH
            }
            mPaintNodeMark.apply {
                color = context!!.getColor(R.color.color_mark) // Not error
                strokeWidth = POINT_SIZE
            }
            mPaintLineMark.apply {
                color = context!!.getColor(R.color.color_mark) // Not error
                strokeWidth = LINE_WIDTH
            }
            mPaintMarkCircle.apply {
                color = context!!.getColor(R.color.color_mark) // Not error
                style = Paint.Style.STROKE
                strokeWidth = LINE_WIDTH
            }
            mPaintText.apply {
                color = context!!.getColor(R.color.canvas_text_color) // Not error
                textSize = TEXT_SIZE
            }
            mPaintAngle.apply {
                color = context!!.getColor(R.color.color_angle_arc) // Not error
                strokeWidth = LINE_WIDTH
                style = Paint.Style.STROKE
            }
        }

    private fun getDimen(dimenId: Int) = App.instance.resources.getDimension(dimenId)

    val POINT_SIZE: Float = getDimen(R.dimen.POINT_SIZE)
    val LINE_WIDTH: Float = getDimen(R.dimen.LINE_WIDTH)
    val TEXT_SIZE: Float = getDimen(R.dimen.TEXT_SIZE)
    val TEXT_MARGIN = getDimen(R.dimen.TEXT_MARGIN)
    val ANGLE_ARC_RADIUS = getDimen(R.dimen.ANGLE_ARC_RADIUS)
}