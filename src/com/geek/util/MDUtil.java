package com.geek.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDUtil {
	/**
	 * 描述：MD5加密. (全大写字母)32
	 * 
	 * @param string
	 *            要加密的字符串
	 * @return String 加密的字符串
	 */
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString().toUpperCase();
	}
}
