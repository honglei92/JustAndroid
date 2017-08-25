package com.boco.whl.funddemo.utils;

import java.util.regex.Pattern;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/2
 *     desc  : 正则相关工具类
 *     If u want more please visit http://toutiao.com/i6231678548520731137/
 * </pre>
 */
public class ValidityUT {
    private static ValidityUT validityUT = null;

    private ValidityUT() {
    }

    public synchronized static ValidityUT getInstance() {
        if (validityUT == null) {
            validityUT = new ValidityUT();
        }
        return validityUT;
    }

    /**
     * ===================================================================
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     *
     * @param input 判断的字符串内容
     * @return boolean 若输入字符串为null或空字符串，返回true
     */
    public boolean isEmpty(String input) {
        if (input == null || "".equals(input) || input.length() == 0)
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证手机号（简单）
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isMobileSimple(String string) {
        return isMatch(ConstUT.getInstance().REGEX_MOBILE_SIMPLE, string);
    }

    /**
     * 验证手机号（精确）
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isMobileExact(String string) {
        return isMatch(ConstUT.getInstance().REGEX_MOBILE_EXACT, string);
    }

    /**
     * 验证电话号码
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isTel(String string) {
        return isMatch(ConstUT.getInstance().REGEX_TEL, string);
    }

    /**
     * 验证身份证号码15位
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isIDCard15(String string) {
        return isMatch(ConstUT.getInstance().REGEX_IDCARD15, string);
    }

    /**
     * 验证身份证号码18位
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isIDCard18(String string) {
        return isMatch(ConstUT.getInstance().REGEX_IDCARD18, string);
    }

    /**
     * 验证邮箱
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isEmail(String string) {
        return isMatch(ConstUT.getInstance().REGEX_EMAIL, string);
    }

    /**
     * 验证URL
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isURL(String string) {
        return isMatch(ConstUT.getInstance().REGEX_URL, string);
    }

    /**
     * 验证汉字
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isChz(String string) {
        return isMatch(ConstUT.getInstance().REGEX_CHZ, string);
    }

    /**
     * 验证用户名
     * <p>取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位</p>
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isUsername(String string) {
        return isMatch(ConstUT.getInstance().REGEX_USERNAME, string);
    }

    /**
     * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isDate(String string) {
        return isMatch(ConstUT.getInstance().REGEX_DATE, string);
    }

    /**
     * 验证IP地址
     *
     * @param string 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isIP(String string) {
        return isMatch(ConstUT.getInstance().REGEX_IP, string);
    }

    /**
     * string是否匹配regex
     *
     * @param regex  正则表达式字符串
     * @param string 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isMatch(String regex, String string) {
        return !ValidityUT.getInstance().isEmpty(string) && Pattern.matches(regex, string);
    }
}