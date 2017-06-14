package com.geek.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	private static SimpleDateFormat format;
	
	/**
	 * ��ǰʱ�� ��: 2013-04-22 10:37
	 * @return
	 */
	public static String getCurrentDate() {
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * ��ȡ��ǰʱ���ǰ����Сʱ yyyy-MM-dd HH:mm
	 * @param hour Сʱ
	 * @return
	 */
	public static String  getCurrentAgeTime(int hour){
		Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY ָʾһ���е�Сʱ */
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}
}
