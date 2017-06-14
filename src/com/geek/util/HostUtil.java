package com.geek.util;

import javax.servlet.http.HttpServletRequest;

public class HostUtil {
	
	/**
	 * 获取地址
	 * @param request
	 * @return
	 */
	public static String getHostIp(HttpServletRequest request){
		String str = "http://"+request.getLocalAddr()+":"+ request.getLocalPort()+"/MySSM";//内网
//		String str = "http://1681654sp5.iask.in:29384"+"/MySSM";//外网
		System.out.println("hostPath:"+str);
		return str;
	}
}
