package com.geek.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	private static SimpleDateFormat format;
	
	/**
	 * 当前时间 如: 2013-04-22 10:37
	 * @return
	 */
	public static String getCurrentDate() {
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 获取当前时间的前几个小时 yyyy-MM-dd HH:mm
	 * @param hour 小时
	 * @return
	 */
	public static String  getCurrentAgeTime(int hour){
		Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY 指示一天中的小时 */
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}
}
