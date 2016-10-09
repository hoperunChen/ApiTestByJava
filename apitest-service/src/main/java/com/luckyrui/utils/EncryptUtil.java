package com.luckyrui.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * 
 * @author chenrui
 * @date 2016年10月8日下午4:31:37
 * @version 201610
 */
public class EncryptUtil {

	/**
	 * md5加密
	 * 
	 * @param input
	 * @return
	 * @author chenrui
	 * @date 2016年10月8日 下午4:39:21
	 * @version 201610
	 */
	public static String md5(String input) {
		return md5(input, Charset.defaultCharset());
	}

	/**
	 * md5加密
	 * @param input
	 * @param charset
	 * @return
	 * @author chenrui
	 * @date 2016年10月8日 下午4:39:45
	 * @version 201610
	 */
	public static String md5(String input, String charset) {
		return md5(input, Charset.forName(charset));
	}

	/**
	 * MD5加密
	 * @param input
	 * @param charset
	 * @return
	 * @author chenrui
	 * @date 2016年10月8日 下午4:40:12
	 * @version 201610
	 */
	private static String md5(String input, Charset charset) {
		MessageDigest localMessageDigest = null;
		try {
			localMessageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		localMessageDigest.update(input.getBytes(charset));
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] message = localMessageDigest.digest();
		char[] chars = new char[32];
		int i = 0;
		for (int j = 0; j < 16; j++) {
			int k = message[j];
			chars[(i++)] = str[(k >>> 4 & 0xF)];
			chars[(i++)] = str[(k & 0xF)];
		}
		return new String(chars);
	}

}
