package com.boco.whl.rxjavademo.utils;

import java.text.NumberFormat;

/**
 * Created by zhang.w.x on 2017/4/6.
 */
public class NumberUtils {
    public static String formatNumber(double num) {
        NumberFormat formater = NumberFormat.getPercentInstance();
        formater.setMaximumIntegerDigits(3);
        formater.setMaximumFractionDigits(1);
        formater.setMinimumFractionDigits(1);

        return formater.format(num);
    }
}
