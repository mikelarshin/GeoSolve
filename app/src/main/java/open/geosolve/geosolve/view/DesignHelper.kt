package open.geosolve.geosolve.view

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.TextAppearanceSpan
import open.geosolve.geosolve.App
import open.geosolve.geosolve.R
import open.geosolve.geosolve.model.canvas.data.Angle
import open.geosolve.geosolve.model.canvas.data.generalized.SolveGraph
import open.geosolve.geosolve.view.rules.Rule
import kotlin.math.roundToInt

fun formatSolveText(templateId: Int, order: List<SolveGraph>, by: (SolveGraph) -> String = { element -> element.toString() }) =
    SpannableStringBuilder().apply {
        append(formatAllDigital(templateId, R.style.Bold))

        for (solveGraph in order) {
            val index = indexOf('%')
            val style = if (solveGraph == order[0]) R.style.AnswerText else R.style.Bold

            replace(index, index + 2, formatText(by(solveGraph), style))
        }

        subSequence(0, length)
    }


fun formatVerbal(rule: Rule) = formatSolveText(rule.verbalID, rule.verbalOrder)

fun formatFormula(rule: Rule) = formatSolveText(rule.expressionID, rule.expressionOrder)

fun formatExpression(rule: Rule) = formatSolveText(rule.expressionID, rule.expressionOrder) { formatValueString(it) }

fun formatExample(exampleText: CharSequence, exampleOrder: List<SolveGraph>): CharSequence = // TODO(rewrite this shit)
    SpannableStringBuilder().apply {
        append(exampleText)

        for (solveGraph in exampleOrder) {
            val index = indexOf('%')
            val style = R.style.Bold

            replace(index, index + 2, formatText(formatValueString(solveGraph, 0), style))
        }

        setSize(subSequence(0, length), R.dimen.BIG_TEXT_SIZE)
    }

fun setSize(text: CharSequence, dimenId: Int): CharSequence =
    SpannableStringBuilder().apply {
        append(text)
        setSpan(AbsoluteSizeSpan(getDimen(dimenId).toInt()), 0, length, 0)
        subSequence(0, length)
    }

fun formatAllDigital(templateId: Int, styleId: Int): CharSequence =
    SpannableStringBuilder().apply {
        append(formatText(getText(templateId), R.style.TemplateText))

        for (charIndex in 0..length - 2) {
            val char = get(charIndex)
            val style = if (char.isDigit()) styleId else R.style.TemplateText

            replace(charIndex, charIndex + 2, formatText(get(charIndex).toString() + get(charIndex + 1), style))
        }
    }

fun getText(textID: Int) = App.context.getString(textID)
fun getDimen(dimenId: Int) = App.context.resources.getDimension(dimenId)

private fun formatText(string: CharSequence, styleId: Int): SpannableString =
    SpannableString(string).apply {
        setSpan(TextAppearanceSpan(App.context, styleId), 0, length, 0)
    }

fun formatAnswer(solveGraph: SolveGraph): CharSequence =
    SpannableStringBuilder().apply {
        append(formatText(solveGraph.toString(), R.style.AnswerText))
        append(formatText(" = ", R.style.TemplateText))
        append(formatText(formatValueString(solveGraph), R.style.AnswerText))

        subSequence(0, length)
    }

fun formatAlertMessage(messageId: Int, element: String): CharSequence =
    SpannableStringBuilder().apply {
        val templateText = getText(messageId) + " "
        append(formatText(templateText, R.style.TemplateText))
        append(formatText(element, R.style.AnswerText))

        subSequence(0, length)
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