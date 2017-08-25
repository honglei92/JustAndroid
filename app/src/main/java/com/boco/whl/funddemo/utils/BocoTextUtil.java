package com.boco.whl.funddemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhang.w.x on 2017/3/21.
 */
public class BocoTextUtil {
    /**
     * 电话号码验证
     * @param number
     * @return
     */
    public static boolean isPhoneNumber(String number){
        String regEx = "^[1][0-9]{10}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(number);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }
}
