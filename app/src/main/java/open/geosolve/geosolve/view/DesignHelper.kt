package open.geosolve.geosolve.view

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.TextAppearanceSpan
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.data.Angle
import open.geosolve.geosolve.model.data.generalized.SolveGraph
import open.geosolve.geosolve.view.rules.Rule
import kotlin.math.roundToInt

fun formatSolveText(templateId: Int, order: List<SolveGraph>, by: (SolveGraph) -> String = { element -> element.toString() }): CharSequence {
    val sb = SpannableStringBuilder().append(formatAllDigital(templateId, R.style.Bold))

    for (solveGraph in order) {
        val index = sb.indexOf('%')
        val style = if (solveGraph == order[0]) R.style.AnswerText else R.style.Bold

        sb.replace(index, index + 2, formatText(by(solveGraph), style))
    }
    return sb.subSequence(0, sb.length)
}

fun formatVerbal(rule: Rule) = formatSolveText(rule.verbalID, rule.verbalOrder)

fun formatFormula(rule: Rule) = formatSolveText(rule.expressionID, rule.expressionOrder)

fun formatExpression(rule: Rule) = formatSolveText(rule.expressionID, rule.expressionOrder) { formatValueString(it) }

fun formatExample(exampleText: CharSequence, exampleOrder: List<SolveGraph>): CharSequence {
    val sb = SpannableStringBuilder().append(exampleText)

    for (solveGraph in exampleOrder) {
        val index = sb.indexOf('%')
        val style = R.style.Bold

        sb.replace(index, index + 2, formatText(formatValueString(solveGraph, 0), style))
    }

    return setSize(sb.subSequence(0, sb.length), R.dimen.BIG_TEXT_SIZE)
}

fun formatExample(exampleTextID: Int, exampleOrder: List<SolveGraph>): CharSequence {
    return formatExample(getText(exampleTextID), exampleOrder)
}
fun setSize(text: CharSequence, dimenId: Int): CharSequence {
    val sb = SpannableStringBuilder().append(text)

    sb.setSpan(AbsoluteSizeSpan(getDimen(dimenId).toInt()), 0, sb.length, 0)
    return sb.subSequence(0, sb.length)
}

fun formatAllDigital(templateId: Int, styleId: Int): CharSequence {
    val sb = SpannableStringBuilder().append(formatText(getText(templateId), R.style.TemplateText))

    for (charIndex in 0..sb.length - 2) {
        val char = sb[charIndex]
        val style = if (char.isDigit()) styleId else R.style.TemplateText

        sb.replace(charIndex, charIndex + 2, formatText(sb[charIndex].toString() + sb[charIndex + 1], style))
    }

    return sb
}

fun getText(textID: Int) = App.context.getString(textID)
fun getDimen(dimenId: Int) = App.context.resources.getDimension(dimenId)

private fun formatText(string: CharSequence, styleId: Int): SpannableString {
    val spannableString = SpannableString(string)
    spannableString.setSpan(TextAppearanceSpan(App.context, styleId), 0, string.length, 0)
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
    val templateText = getText(messageId) + " "

    val sb = SpannableStringBuilder()
        .append(formatText(templateText, R.style.TemplateText))
        .append(formatText(element, R.style.AnswerText))
    return sb.subSequence(0, sb.length)
}

fun formatValueString(solveGraph: SolveGraph, round: Int = 1): String {
    val value: Float = solveGraph.getValue()!!

    val add_note = if (solveGraph is Angle) "°" else ""

    val roundValue = "%.${round}f".format(value)

    return (
            if (roundValue.last() == '0')
                value.roundToInt().toString()
            else
                roundValue) + add_note
}