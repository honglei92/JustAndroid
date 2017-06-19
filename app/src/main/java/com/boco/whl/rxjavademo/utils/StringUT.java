package com.boco.whl.rxjavademo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串帮助类
 * <p>
 * 包含了拼接、去除、替换、判断、转换等方法
 */
public class StringUT {

    private static StringUT stringUT = null;

    public final int TYPE_NUMBER = 0;
    public final int TYPE_ENGLISH = 1;
    public final int TYPE_FUHAO = 2;
    public final int TYPE_CHINA = 3;

    private StringUT() {
    }

    public synchronized static StringUT getInstance() {
        if (stringUT == null) {
            stringUT = new StringUT();
        }
        return stringUT;
    }

    /**
     * 首字母小写
     *
     * @param string 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String string) {
        if (ValidityUT.getInstance().isEmpty(string) || !Character.isUpperCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) + 32)) + string.substring(1);
    }
    /**
     * ===================================================================
     * 字符串尾部拼接
     *
     * @param num
     * @param prefix
     * @return
     */
    public String addPrefix(int num, String prefix) {
        return num < 10 ? prefix + num : String.valueOf(num);
    }

    public String addPrefix(String numStr, String prefix) {
        return addPrefix(Integer.parseInt(numStr), prefix);
    }

    public String addPrefixZero(int num) {
        return addPrefix(num, "0");
    }

    public String addPrefixZero(String numStr) {
        return addPrefix(numStr, "0");
    }

    public String addPrefixHtmlSpace(int num) {
        return addPrefix(num, "&nbsp;");
    }

    public String addPrefixHtmlSpace(String numStr) {
        return addPrefix(numStr, "&nbsp;");
    }

    /**
     * ===================================================================
     * 数组拼接成字符串 中间以自定义字符串连接
     *
     * @param data   需要连接的数据
     * @param symbol 连接符
     * @return String
     */
    public String commaInt(Object[] data, String symbol) {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sbf.append(data[i]);
            if (i < data.length - 1) {
                sbf.append(symbol);
            }
        }
        return sbf.toString();
    }

    /**
     * ===================================================================
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 检查字符串是否为null或空，若为空则返回""，不为空返回原有内容。
     *
     * @param input
     * @return
     */
    public String inspectEmpty(String input) {
        if (ValidityUT.getInstance().isEmpty(input))
            return "";
        return input;
    }

    /**
     * bytes[]转换成Hex字符串,可用于URL转换，IP地址转换.
     */
    public String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * ===================================================================
     * 获取数据大小表示的数值
     *
     * @param value
     * @return
     */
    public String prettyBytes(long value) {
        String args[] = {"B", "KB", "MB", "GB", "TB"};
        StringBuilder sb = new StringBuilder();
        int i;
        if (value < 1024L) {
            sb.append(String.valueOf(value));
            i = 0;
        } else if (value < 1048576L) {
            sb.append(String.format("%.1f", value / 1024.0));
            i = 1;
        } else if (value < 1073741824L) {
            sb.append(String.format("%.2f", value / 1048576.0));
            i = 2;
        } else if (value < 1099511627776L) {
            sb.append(String.format("%.3f", value / 1073741824.0));
            i = 3;
        } else {
            sb.append(String.format("%.4f", value / 1099511627776.0));
            i = 4;
        }
        sb.append(' ');
        sb.append(args[i]);
        return sb.toString();
    }

    /**
     * ===================================================================
     * 判断字符串重复次数
     *
     * @param str
     * @param times
     * @return String
     */
    public String repeat(String str, int times) {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sbf.append(str);
        }
        return sbf.toString();
    }

    /**
     * ===================================================================
     * 获得数组中最长的字符串的长度
     *
     * @param keys
     * @return int
     */
    public int getLargestLengthInArray(String[] keys) {
        int length = 0;
        for (String key : keys) {
            if (key.length() > length) {
                length = key.length();
            }
        }
        return length;
    }

    /**
     * 获得数组中最长的字符串
     *
     * @param keys
     * @return
     */
    public String getLargestValueInArray(String[] keys) {
        String str = "";
        int length = 0;
        for (String key : keys) {
            if (key.length() > length) {
                str = key;
                length = key.length();
            }
        }
        return str;
    }

    /**
     * ===================================================================
     * 替换string中的指定字符串
     *
     * @param strSearch
     * @param replacement
     * @param body
     * @return String
     */
    public String replaceAllByStringBuffer(String strSearch, String replacement, String body) {
        StringBuilder sbf = new StringBuilder(body);
        int index = 0;
        int offset = 0;
        do {
            index = sbf.indexOf(strSearch, offset);
            if (index > -1) {
                sbf.replace(index, index + strSearch.length(), replacement);
                /**
                 * 下一次开始的点是index加上置换后的字符串的长度
                 */
                offset = index + replacement.length();
            }
        } while (index > -1);

        return sbf.toString();
    }

    /**
     * 字符串替换
     *
     * @param strSrc 要进行替换操作的字符串
     * @param strOld 要查找的字符串
     * @param strNew 要替换的字符串
     * @return 替换后的字符串
     * <pre>
     */
    public final String replace(String strSrc, String strOld, String strNew) {
        if (strSrc == null || strOld == null || strNew == null)
            return "";
        int i = 0;
        if (strOld.equals(strNew)) //避免新旧字符一样产生死循环
            return strSrc;
        if ((i = strSrc.indexOf(strOld, i)) >= 0) {
            char[] arr_cSrc = strSrc.toCharArray();
            char[] arr_cNew = strNew.toCharArray();
            int intOldLen = strOld.length();
            StringBuilder buf = new StringBuilder(arr_cSrc.length);
            buf.append(arr_cSrc, 0, i).append(arr_cNew);
            i += intOldLen;
            int j = i;
            while ((i = strSrc.indexOf(strOld, i)) > 0) {
                buf.append(arr_cSrc, j, i - j).append(arr_cNew);
                i += intOldLen;
                j = i;
            }
            buf.append(arr_cSrc, j, arr_cSrc.length - j);
            return buf.toString();
        }
        return strSrc;
    }

    /**
     * ===================================================================
     * 判断 char c 是汉字还是数字 还是字母
     *
     * @param c
     * @return int
     */
    public int sepMarkNot(char c) {
        // 数字 48-57
        if (c > 47 && c < 58) {
            return TYPE_NUMBER;
        }
        // 大写字母 65-90
        if (c > 64 && c < 91) {
            return TYPE_ENGLISH;
        }
        // 小写字母 97-122
        if (c > 96 && c < 122) {
            return TYPE_ENGLISH;
        }
        // 汉字（简体）
        if (c >= 0x4e00 && c <= 0x9fbb) {
            return TYPE_CHINA;
        }
        return TYPE_FUHAO;
    }

    /**
     * ===================================================================
     * 判断字节数 汉字2个字节英文1个字节
     *
     * @param content
     * @return int
     */
    public int getLengths(String content) {
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (sepMarkNot(content.charAt(i)) == TYPE_CHINA) {
                count = count + 2;
            } else {
                count = count + 1;
            }
        }
        return count;
    }

    /**
     * ===================================================================
     * 首字母大写
     * <p/>
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public String capitalizeFirstLetter(String str) {
        if (ValidityUT.getInstance().isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : String.valueOf(Character.toUpperCase(c)) + str.substring(1);
    }

    /**
     * ===================================================================
     * encoded(utf-8)转码 如果失败 则返回默认值defultReturn
     * <p/>
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public String utf8Encode(String str, String defultReturn) {
        if (!ValidityUT.getInstance().isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * ===================================================================
     * html格式化字符串
     * <p/>
     * <pre>
     * getHrefInnerHtml(null) = ""
     * getHrefInnerHtml("") = ""
     * getHrefInnerHtml("mp3") = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;") = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ") = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;") = "innerHtml2";
     * </pre>
     *
     * @param href
     * @return <ul>
     * <li>if href is null, return ""</li>
     * <li>if not match regx, return source</li>
     * <li>return the last string that match regx</li>
     * </ul>
     */
    public String getHrefInnerHtml(String href) {
        if (ValidityUT.getInstance().isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * ===================================================================
     * 处理html中的特殊字符
     * <p/>
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     *
     * @param source
     * @return
     */
    public String htmlEscapeCharsToString(String source) {
        return ValidityUT.getInstance().isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * ===================================================================
     * 转换半角到全角
     * <p/>
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     *
     * @param s
     * @return
     */
    public String fullWidthToHalfWidth(String s) {
        if (ValidityUT.getInstance().isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 转换全角到半角
     * <p/>
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     *
     * @param s
     * @return
     */
    public String halfWidthToFullWidth(String s) {
        if (ValidityUT.getInstance().isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * ===================================================================
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 字符串转布尔值
     *
     * @param str
     * @return 转换异常返回 false
     */
    public boolean toBool(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * ===================================================================
     * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     *
     * @param strSrc 要进行替换操作的字符串
     * @return 替换特殊字符后的字符串
     */

    public String htmlEncode(String strSrc) {
        if (strSrc == null)
            return "";
        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;
        for (char anArr_cSrc : arr_cSrc) {
            ch = anArr_cSrc;
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"')
                buf.append("&quot;");
            else if (ch == '\'')
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }
        return buf.toString();
    }

    /**
     * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     *
     * @param strSrc 要进行替换操作的字符串
     * @param quotes 为0时单引号和双引号都替换，为1时不替换单引号，为2时不替换双引号，为3时单引号和双引号都不替换
     * @return 替换特殊字符后的字符串
     */
    public String htmlEncode(String strSrc, int quotes) {
        if (strSrc == null)
            return "";
        if (quotes == 0) {
            return htmlEncode(strSrc);
        }
        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;
        for (char anArr_cSrc : arr_cSrc) {
            ch = anArr_cSrc;
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"' && quotes == 1)
                buf.append("&quot;");
            else if (ch == '\'' && quotes == 2)
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }
        return buf.toString();
    }

    /**
     * 和htmlEncode正好相反
     *
     * @param strSrc 要进行转换的字符串
     * @return 转换后的字符串
     */
    public String htmlDecode(String strSrc) {
        if (strSrc == null)
            return "";
        strSrc = strSrc.replaceAll("&lt;", "<");
        strSrc = strSrc.replaceAll("&gt;", ">");
        strSrc = strSrc.replaceAll("&quot;", "\"");
        strSrc = strSrc.replaceAll("&#039;", "'");
        strSrc = strSrc.replaceAll("&amp;", "&");
        return strSrc;
    }
}
