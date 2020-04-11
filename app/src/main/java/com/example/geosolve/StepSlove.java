package com.example.geosolve;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;

public class StepSlove {
    CharSequence template;
    CharSequence rule;

    public StepSlove(String template, String rule, String... args) {
        SpannableStringBuilder sb = new SpannableStringBuilder().append(formatString(template, R.style.Text));
        for (String item : args) {
            int index = sb.toString().indexOf("%");
            sb.replace(index, index + 2, formatString(item, R.style.BoldText));
        }

        this.template = sb.subSequence(0, sb.length());
        this.rule = formatString(rule, R.style.Text).subSequence(0, rule.length());
    }

    private SpannableString formatString(String string, int styleId) {
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new TextAppearanceSpan(RecycleAdapter.getContext(), styleId), 0, string.length(), 0);
        return spannableString;
    }
}
