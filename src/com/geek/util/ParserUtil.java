package com.geek.util;


import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @category �������ݽ���
 * create at 2016��8��24�� ����3:50:50
 */
public class ParserUtil {
	
	/**
	 * JSON�����ɶ���
	 * @param text JSON�ַ���
	 * @param clazz JavaBean
	 * @return JavaBean�Ķ���
	 */
	public static <T>Object jsonToObject(String text, Class<T> clazz){
		Object object=JSONObject.parseObject(text,clazz);
		return object;
	}
	
	
	/**
	 * JSON�����ɶ���ļ���
	 * @param text JSON�ַ���
	 * @param clazz JavaBean
	 * @return JavaBean�Ķ���ļ���
	 */
	public static <T> List<T> jsonToList(String text, Class<T> clazz){
		List<T> list=JSONObject.parseArray(text, clazz);
		return list;
	}
	
	
	/**
	 * ��������JSON
	 * @param object JavaBean�Ķ���
	 * @return JSON�ַ���
	 */
	public static String objectToJson(Object object){
		String json=JSONObject.toJSONString(object);
		return json;
	}
	
	/**
	 * ���󼯺�����JSON
	 * @param <T>JavaBean
	 * @param list JavaBean����ļ���
	 * @param prettyFormat �Ƿ����л�
	 * @return JSON�ַ���
	 */
	public static <T> String arrayObjectToJson(List<T> list, boolean prettyFormat){
		String json=JSON.toJSONString(list, prettyFormat);
		return json;
	}
}
