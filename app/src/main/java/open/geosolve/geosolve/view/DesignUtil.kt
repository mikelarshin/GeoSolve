package open.geosolve.geosolve.view

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.generalized.SolveGraph

object DesignUtil {

    fun formatSolve(templateVerbalId: Int, templateExpressionId: Int, vararg formatArgs: SolveGraph): Array<out () -> CharSequence> {

        fun format_answer(templateId: Int, by: (SolveGraph) -> String = { element -> element.toString() }): CharSequence {
            val sb = SpannableStringBuilder().append(
                formatText(
                    App.instance.getString(templateId),
                    R.style.TemplateText)
            )

            for (i in 0 until sb.filter { it == '%' }.length) {
                val index = sb.indexOf('%')
                val style = if (i == 0) R.style.AnswerText else R.style.Bold
                sb.replace(index, index + 2, formatText(by(formatArgs[i]), style))
            }
            return sb.subSequence(0, sb.length)
        }

        val formula: CharSequence = format_answer(templateExpressionId)
        val verbal: CharSequence = format_answer(templateVerbalId)

        return arrayOf(
            { formula },
            { verbal },
            { format_answer(templateExpressionId) { formatValueString(it) } })
    }

    private fun formatText(string: String, styleId: Int): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(TextAppearanceSpan(App.instance, styleId), 0, string.length, 0)
        return spannableString
    }

    fun formatAnswer(solveGraph: SolveGraph): CharSequence {
        val sb = SpannableStringBuilder()
            .append(formatText(solveGraph.toString(), R.style.AnswerText))
            .append(formatText(" = ", R.style.TemplateText))
            .append(formatText(formatValueString(solveGraph), R.style.AnswerText))

        return sb.subSequence(0, sb.length)
    }

    fun formatAlertMessage(messageId: Int, element: String): CharSequence {
        val templateText = App.instance.getString(messageId) + " "

        val sb = SpannableStringBuilder()
            .append(formatText(templateText, R.style.TemplateText))
            .append(formatText(element, R.style.AnswerText))
        return sb.subSequence(0, sb.length)
    }

    fun formatValueString(solveGraph: SolveGraph): String {
        val value: Float = solveGraph.getValue()!!

        val add_note =  if (solveGraph is Angle) "°" else ""

        return (if (value - value.toInt() < 0.1f) value.toInt().toString() else "%.1f".format(value)) + add_note
    }
}