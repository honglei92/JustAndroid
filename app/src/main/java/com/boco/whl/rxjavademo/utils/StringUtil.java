package com.boco.whl.rxjavademo.utils;

/**
 * Created by honglei92 on 2017/4/14 0014.
 */

/*
 * String是实现了Serializable接口的，所以String可以用在 当你想把的内存中的对象写入到硬盘的时候;
 * 当你想用套接字在网络上传送对象的时候;当你想通过RMI传输对象的时候
 */

/**
 * @author honglei92
 */
public class StringUtil {
    /**
     * 超过字符用...替代
     *
     * @param string
     * @param i
     * @return
     */
    public static String overToEllipsis(String string, int i) {
        if (string.length() > i) {
            string = string.replace(string.substring(i - 1, string.length()),
                    "...");
        }
        return string;
    }

    /**
     * 等于null时返回""
     *
     * @param str
     * @return
     */
    public static String getNull(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }
}