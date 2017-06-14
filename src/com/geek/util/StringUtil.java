package com.geek.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		return (s == null || s.length()==0);
	}
	
	/**
	 * 返回指定类型的时间字符串
	 * @param format
	 * @return
	 */
	public static String getTimeString(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowTime(){
		return getTimeString("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * (伪随机)生成32位uuid(它保证对在同一时空中的所有机器都是唯一的)
	 * @return
	 */
	public static String getUUID(){
//		return UUID.randomUUID().toString().replace("-", "");
		String str = UUID.randomUUID().toString();
		return str.replace("-", "");
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			System.out.println(getUUID());
		}
	}
	
	public static String joinArr(String[] arr,String c) {
		String chr = "";
		String result = "";
		for (String str : arr) {
			result += chr + str;
			chr = c;
		}
		return result;
	}
	public static String joinArr(List<String> list,String c) {
		String chr = "";
		String result = "";
		for (String str : list) {
			result += chr + str;
			chr = c;
		}
		return result;
	}
	public static boolean chkIndex(String[] arr,int index) {
		if (arr == null) {
			return false;
		}
		if (arr.length <= index) {
			return false;
		}
		return true;
	}
}
