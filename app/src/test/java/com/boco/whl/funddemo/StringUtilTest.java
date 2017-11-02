package com.boco.whl.funddemo;

import com.boco.whl.funddemo.utils.StringUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * author:     wanghonglei@boco.com.cn
 * desc:       描述-
 * createTime: 2017/10/12 0012
 * updateTime: 2017/10/12 0012
 */

public class StringUtilTest {
    @Test
    public void ellipse_isCorrect() throws Exception {
        String name = "尼古拉斯凯奇大帝";
        String result = "尼古拉斯凯...";
        System.out.print("转换的结果是:" + StringUtil.overToEllipsis(name, 6));
        assertEquals(result, StringUtil.overToEllipsis(name, 6));
    }
}
