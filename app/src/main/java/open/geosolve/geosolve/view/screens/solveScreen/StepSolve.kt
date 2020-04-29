package open.geosolve.geosolve.view.screens.solveScreen

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import open.geosolve.geosolve.R

// TODO(delete context from constructor)
// TODO(create util method solve_format(String, args) -> CharSequence)

class StepSolve(
    templateVerbal: String,
    templateExpression: String,
    val useList: MutableList<*>?,
    vararg args: String,
    val context: Context
) {

    lateinit var verbal: CharSequence
    val expression: CharSequence

    init {
        if (args.isNotEmpty()) {
            var sb = SpannableStringBuilder()
                .append(
                    formatString(
                        templateVerbal,
                        R.style.Text
                    )
                )

            for (item in args) {
                var index = sb.toString().indexOf("%")
                if (index == -1) {
                    this.verbal = sb.subSequence(0, sb.length)
                    sb = SpannableStringBuilder().append(
                        formatString(
                            templateExpression,
                            R.style.Text
                        )
                    )
                    index = sb.toString().indexOf("%")
                }
                sb.replace(
                    index, index + 2, formatString(
                        item,
                        R.style.BoldText
                    )
                )
            }
            this.expression = sb.subSequence(0, sb.length)
        } else {
            this.verbal = formatString(
                templateVerbal,
                R.style.Text
            )
            this.expression = formatString(
                templateExpression,
                R.style.Text
            )
        }
    }

    private fun formatString(string: String, styleId: Int): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(TextAppearanceSpan(context, styleId), 0, string.length, 0)
        return spannableString
    }


    //    fun useToCanvas(){
//        for(item in useList)
//            when(item){
//                is Node -> canvas.drawPoint(...)
//                is Line -> canvas.drawLine(...)
//            }
//    }
}