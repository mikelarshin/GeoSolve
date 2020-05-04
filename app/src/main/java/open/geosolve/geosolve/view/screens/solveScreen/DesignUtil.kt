package open.geosolve.geosolve.view.screens.solveScreen

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.Element

object DesignUtil {

    fun formatSolve(templateVerbalId: Int, templateExpressionId: Int, vararg formatArgs: Element)
            : Array<out () -> CharSequence> {

        fun getFormat(templateId: Int, by: (Element) -> String = { element -> element.toString() }): CharSequence {
            val sb = SpannableStringBuilder().append(formatText(App.instance.getString(templateId), R.style.TemplateText))

            for (i in 0 until sb.filter { it == '%' }.length) {
                val index = sb.indexOf('%')
                sb.replace(index, index + 2, formatText(by(formatArgs[i]), R.style.Bold))
            }
            return sb.subSequence(0, sb.length)
        }

        val formula: CharSequence = getFormat(templateExpressionId)
        val verbal: CharSequence = getFormat(templateVerbalId)

        return arrayOf(
            { formula },
            { verbal },
            { getFormat(templateExpressionId, { element -> formatValueString(element) }) })
    }

    private fun formatText(string: String, styleId: Int): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(TextAppearanceSpan(App.instance, styleId), 0, string.length, 0)
        return spannableString
    }

    fun formatAnswer(element: Element): CharSequence {
        val sb = SpannableStringBuilder()
            .append(formatText(element.toString(), R.style.AnswerText))
            .append(formatText(" = ", R.style.TemplateText))
            .append(formatText(formatValueString(element), R.style.AnswerText))

        return sb.subSequence(0, sb.length)
    }

    fun formatValueString(element: Element): String {
        val value: Float = element.getValue()!!

        val add_note =  if (element is Angle) "°" else ""

        return (if (value - value.toInt() < 0.1f) value.toInt().toString() else "%.1f".format(value)) + add_note
    }
}