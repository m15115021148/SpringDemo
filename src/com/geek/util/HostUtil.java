package com.geek.util;

import javax.servlet.http.HttpServletRequest;

public class HostUtil {
	
	/**
	 * ��ȡ��ַ
	 * @param request
	 * @return
	 */
	public static String getHostIp(HttpServletRequest request){
		String str = "http://"+request.getLocalAddr()+":"+ request.getLocalPort()+"/MySSM";//����
//		String str = "http://1681654sp5.iask.in:29384"+"/MySSM";//����
		System.out.println("hostPath:"+str);
		return str;
	}
}
