package com.boco.whl.rxjavademo.utils.commonutil.string;

public class StringUtil {

	// 解析|| 业务
	public static String splitFlag(String str) {
		String temp = "";
		if (str.contains("||")) {
			temp = str.split("\\|\\|")[1];
		} else {
			temp = str;
		}

		return temp;
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
