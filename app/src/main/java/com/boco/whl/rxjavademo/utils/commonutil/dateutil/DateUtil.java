package com.boco.whl.rxjavademo.utils.commonutil.dateutil;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * 
 * <p>
 * Title: 网管采集平台
 * </p>
 * 
 * <p>
 * Description: 配置各种算法
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * 
 * <p>
 * Company: 亿阳成都公司
 * </p>
 * 
 * @author 王天聪
 * @version 1.0
 */
public class DateUtil {
	public static final int SECOND = 1000;

	public static final int MINUTE = SECOND * 60;

	public static final int HOUR = MINUTE * 60;

	public static final int DAY = HOUR * 24;

	public static final int WEEK = DAY * 7;

	public static final int YEAR = DAY * 365; // or 366 ???

	private static final String DEF_OUT_FORMAT = "yyyy-MM-dd";

	/**
	 * 一天中的天数
	 */
	public static long millionSecondsOfDay = 86400000;

	/**
	 * 得到日期的前后几天
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date getDate(Date date, int i) {
		// long seconds = date.getTime() + i * 86400000;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, i);

		return calendar.getTime();

	}

	/**
	 * 得到日期的前后几天
	 * 
	 * @param date
	 * @param
	 * @return
	 */
	public static Date getDateByHour(Date date, int hour) {
		// long seconds = date.getTime() + i * 86400000;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);

		return calendar.getTime();

	}

	/**
	 * 得到两个日期之间的天数,两头不算,取出数据后，可以根据需要再加
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDay(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()) / millionSecondsOfDay);
	}

	/**
	 * 根据日期取出是星期几
	 * 
	 * @param date
	 *            Date
	 * @return int 返回1-7
	 */
	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar
				.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 根据 年 月 得到这个月的天数
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param
	 * @return int
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		int monthDays = calendar.getActualMaximum(Calendar.DATE);
		return monthDays;
	}

	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getYearOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonthOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static java.sql.Date getSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 取得没有时间的日期格式为 yyyy-mm-dd
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getDate(Date date) {
		String str = dateToString(date);
		return getDate(str);

	}

	public static List<String> getDates(Date date1, Date date2) {
		int day = getDay(date1, date2);

		List<String> dates = new ArrayList<String>();

		for (int i = 0; i <= day; i++) {
			Date date = getDate(date1, i);
			dates.add(toStringByFormat(date, "yyyy-MM-dd"));
		}
		return dates;
	}

	public static List<String> getFormatDates(Date date1, Date date2,
			String format) {
		int day = getDay(date1, date2);

		List<String> dates = new ArrayList<String>();

		for (int i = 0; i <= day; i++) {
			Date date = getDate(date1, i);
			dates.add(toStringByFormat(date, format));
		}
		return dates;
	}

	/**
	 * 产生唯一日期时间序列
	 * 
	 * @param
	 * @return String
	 */
	public static String getUniueTimeString() {
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
	}

	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	/**
	 * 返回yyyy-MM-dd HH:mm格式的日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String datetimeToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	/**
	 * 返回yyyy-MM-dd HH:mm:ss格式的日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String datetimesToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	public static String toStringByFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String tmpStr = "";
		if (date != null) {
			tmpStr = sdf.format(date);
		}

		return tmpStr;
	}

	public static Date toDateByFormat(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析yyyyMMddHHmm格式日期字符串
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析yyyy-MM-dd HH:mm:ss格式日期字符串
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDateTimeS(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析yyyy-MM-dd HH:mm格式日期字符串
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDateTimeM(String str) {
		if (str == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}

	public static Calendar stringToDateTimeCS(String str) {
		if (str == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDateTimeS(str + ":00"));
		return calendar;
	}

	public static Calendar stringToDateTimeC(String str) {
		if (str == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDateTimeS(str + ":00"));// stringToDateTimeM(str)
		return calendar;
	}

	/**
	 * 解析yyyy-MM-dd格式日期字符串
	 * 
	 * @param str
	 * @return
	 */
	public static Date getDate(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}

	}

	public static String getTimeString(int duration) {
		int hours = duration / DateUtil.HOUR;
		int remain = duration - (hours * DateUtil.HOUR);
		int minutes = remain / DateUtil.MINUTE;
		StringBuffer time = new StringBuffer(64);
		if (hours != 0) {
			if (hours == 1) {
				time.append("1 hour and ");
			} else {
				time.append(hours).append(" hours and ");
			}
		}
		if (minutes == 1) {
			time.append("1 minute");
		} else {
			// what if minutes == 0 ???
			time.append(minutes).append(" minute(s)");
		}
		return time.toString();
	}

	/**
	 * 日期比较小于等于
	 * 
	 * @param
	 */
	public static boolean beforeEqual(Date f, Date t) {
		f = getDate(f);
		t = getDate(t);
		if (f.before(t) || f.equals(t)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 日期比较 大于等于
	 * 
	 * @param
	 */
	public static boolean afterEqual(Date f, Date t) {
		f = getDate(f);
		t = getDate(t);
		if (f.after(t) || f.equals(t)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean between(Date d, Date s, Date e) {
		if (d.before(e) && d.after(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得当天日期
	 * 
	 * @return yyyy-mm-dd
	 */
	public static String getCurrentDateStr() {
		String curDateStr = "";

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		curDateStr = String.valueOf(year) + "-";
		curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String
				.valueOf(month)) + "-";
		curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String
				.valueOf(day));

		return curDateStr;
	}

	/**
	 * 获得当天月份
	 * 
	 * @return yyyy-mm
	 */
	public static String getCurrentMonthStr() {
		String curDateStr = "";

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		@SuppressWarnings("unused")
		int day = cal.get(Calendar.DAY_OF_MONTH);

		curDateStr = String.valueOf(year) + "-";
		curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String
				.valueOf(month));

		return curDateStr;
	}

	public static String format(Date date, String outFormat) {
		return format(date, outFormat, Locale.ENGLISH);
	}

	public static String format(Date date, String outFormat, Locale locale) {
		if (outFormat == null || "".compareTo(outFormat) == 0) {
			outFormat = DEF_OUT_FORMAT;
		}
		SimpleDateFormat outDateFormat = null;
		if (locale == null) {
			outDateFormat = new SimpleDateFormat(outFormat, Locale.ENGLISH);
		} else {
			outDateFormat = new SimpleDateFormat(outFormat, locale);
		}
		String outDate = outDateFormat.format(date);
		return outDate;
	}

	public static Date getBeforetime(Date date, int interval) {
		GregorianCalendar gca = new GregorianCalendar();
		gca.setTime(date);
		gca.add(GregorianCalendar.MONTH, (-1) * interval);
		return gca.getTime();
	}

	// 得到当前时间对应的前一个月时间
	public static String getBeforetime(Date date) {
		return format(getBeforetime(date, 1), DEF_OUT_FORMAT);
	}

	// 得到这个传入月份的第一天 （格式为YYYY-MM-dd）
	public static Date getFirstDateOfMonth(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMinimum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	// 得到这个传入月份的最后一天（格式为YYYY-MM-dd）
	public static Date getEndDateOfMonth(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	// 得到传入月份上个月的第一天（格式为YYYY-MM-dd）
	public static Date getFirstDateOfPreMonth(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		Calendar ca = Calendar.getInstance();
		GregorianCalendar gca = new GregorianCalendar();
		gca.setTime(date);
		gca.add(GregorianCalendar.MONTH, (-1) * 1);
		ca.setTime(gca.getTime());
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMinimum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	// 得到传入月份上个月的最后一天 （格式为YYYY-MM-dd）
	public static Date getEndDateOfPreMonth(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		Calendar ca = Calendar.getInstance();
		GregorianCalendar gca = new GregorianCalendar();
		gca.setTime(date);
		gca.add(GregorianCalendar.MONTH, (-1) * 1);
		ca.setTime(gca.getTime());
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	/**
	 * 返回 yyyy-MM-dd 格式字符串
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getYearMonthDayStr(Date date) {
		if (date == null) 
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

}
