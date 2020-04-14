package com.example.geosolve.model

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import com.example.geosolve.R
import com.example.geosolve.view.RecycleAdapter

class StepSlove(template: String, rule: String, vararg args: String) {
    var expression: CharSequence
    var rule: CharSequence = formatString(rule,
        R.style.Text
    ).subSequence(0, rule.length)

    init {
        val sb = SpannableStringBuilder()
                .append(formatString(template, R.style.Text))

        for (item in args) {
            val index = sb.toString().indexOf("%")
            sb.replace(index, index + 2, formatString(item,
                R.style.BoldText
            ))
        }
        this.expression = sb.subSequence(0, sb.length)
    }

    private fun formatString(string: String, styleId: Int): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(TextAppearanceSpan(RecycleAdapter.context, styleId), 0, string.length, 0)
        return spannableString
    }
}