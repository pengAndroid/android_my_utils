package com.example.shinelon.myutils.utils;

import android.content.res.ColorStateList;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

/**
 * Create by Peng on 2017年11月1日16:42:12
 */

public class TvUtils {
    private static final String TAG = "TvUtils";

    /**
     * 主要用于拼接字体
     *
     * @param tv     字体内容
     * @param tvSize 字体大小
     * @param color  字体颜色
     * @return
     */
    public static SpannableStringBuilder stringToSpannable(String tv, int tvSize, int color) {
        final SpannableStringBuilder sp = new SpannableStringBuilder(tv);
        ColorStateList Colors = ColorStateList.valueOf(0xff2fb9c3);
        sp.setSpan(new TextAppearanceSpan(null, 0, tvSize, Colors, null), 0, tv.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
        return sp;
    }

    /**
     * 主要用于拼接字体(字体颜色大小默认为textView颜色大小)
     *
     * @param tv 字体内容
     * @return
     */
    public static SpannableStringBuilder stringToSpannable(String tv) {
        final SpannableStringBuilder sp = new SpannableStringBuilder(tv);
        sp.setSpan(new TextAppearanceSpan(null, 0, 0, null, null), 0, tv.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
        return sp;
    }

    /**
     * 主要用于拼接字体(字体颜色默认为textView颜色)
     *
     * @param tv     字体内容
     * @param tvSize 字体大小
     * @return
     */
    public static SpannableStringBuilder stringToSpannableSize(String tv, int tvSize) {
        final SpannableStringBuilder sp = new SpannableStringBuilder(tv);
        sp.setSpan(new TextAppearanceSpan(null, 0, tvSize, null, null), 0, tv.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
        return sp;
    }

    /**
     * 主要用于拼接字体(字体大小默认为textView大小)
     *
     * @param tv    字体内容
     * @param color 字体颜色
     * @return
     */
    public static SpannableStringBuilder stringToSpannableColor(String tv, int color) {
        final SpannableStringBuilder sp = new SpannableStringBuilder(tv);
        ColorStateList Colors = ColorStateList.valueOf(color);
        sp.setSpan(new TextAppearanceSpan(null, 0, 0, Colors, null), 0, tv.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
        return sp;
    }


    /**
     * TextView拼接部分字体颜色大小
     *
     * @return
     */
    public static StringToSpannable create() {
        return new StringToSpannable();
    }

    /**
     * TextView拼接部分字体颜色大小的类
     */
    public static class StringToSpannable {
        private SpannableStringBuilder ssb;

        /**
         * 只初始化一次
         */
        public StringToSpannable() {
            ssb = new SpannableStringBuilder();
        }

        public SpannableStringBuilder createSsb() {
            return new SpannableStringBuilder();
        }

        /**
         * 普通拼接
         *
         * @param tv
         */
        public StringToSpannable addSsb(String tv) {
            ssb.append(stringToSpannable(tv));
            return this;
        }

        /**
         * 改变大小颜色的拼接
         *
         * @param tv
         * @param size
         * @param color
         */
        public StringToSpannable addSsb(String tv, int size, int color) {
            ssb.append(stringToSpannable(tv, size, color));
            return this;
        }

        /**
         * 只改变大小的拼接
         *
         * @param tv
         * @param size
         */
        public StringToSpannable addSsbSize(String tv, int size) {
            ssb.append(stringToSpannableSize(tv, size));
            return this;
        }

        /**
         * 只改变颜色的拼接
         *
         * @param tv
         * @param color
         */
        public StringToSpannable addSsbColor(String tv, int color) {
            ssb.append(stringToSpannableColor(tv, color));
            return this;
        }

        /**
         * 显示控件上
         *
         * @param textView
         */
        public void showIn(TextView textView) {
            textView.setText(ssb);
            ssb = null;
        }

    }

}
