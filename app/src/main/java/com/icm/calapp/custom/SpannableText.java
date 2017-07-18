package com.icm.calapp.custom;

import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.icm.calapp.R;

public class SpannableText {

    public static Spannable getSpan(Activity activity, String text, String textIndex) {
        Spannable wordtoSpan = new SpannableString(text);

        int start = text.indexOf(textIndex);
        int end = start + textIndex.length();
        // index
        if (start > 0) {

            wordtoSpan.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.color_text_span))
                    , start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return wordtoSpan;
    }

    public static Spannable getSpan(Activity activity, int text, String textIndex) {
        String textStr = activity.getResources().getString(text);
        Spannable wordtoSpan = new SpannableString(textStr);

        int start = textStr.indexOf(textIndex);
        int end = start + textIndex.length();
        // index
        if (start > 0) {
            wordtoSpan.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.color_text_span))
                    , start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return wordtoSpan;
    }
}
