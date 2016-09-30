package com.luckyrui.utils;

public class StringUtil {

	/**
	 * 判断str是否为空,如果为空使用nullStr
	 * 
	 * @param str 验证的str
	 * @param nullStr 替换的str
	 * @return
	 * @author chenrui
	 * @date 2016年9月3日 下午4:59:37
	 * @version 2016_Anniversary
	 */
	public static String ifnull(String str, String nullStr) {
		if (null == str || str.trim().isEmpty()) {
			return nullStr;
		}
		return str;

	}
}
