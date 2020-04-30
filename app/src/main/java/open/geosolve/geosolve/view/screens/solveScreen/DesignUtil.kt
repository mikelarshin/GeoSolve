package open.geosolve.geosolve.view.screens.solveScreen

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R

object DesignUtil {

    fun formatSolve(templateId: Int, vararg formatArgs: String): CharSequence {
        val sb = SpannableStringBuilder().append(formatText(App.instance.getString(templateId), R.style.TemplateText)) // need context

        for (replacer in formatArgs) {
            val index = sb.indexOf("%")
            sb.replace(index, index + 2, formatText(replacer, R.style.BoldText))
        }

        return sb.subSequence(0, sb.length)
    }

    private fun formatText(string: String, styleId: Int): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(TextAppearanceSpan(App.instance, styleId), 0, string.length, 0)
        return spannableString
    }
}