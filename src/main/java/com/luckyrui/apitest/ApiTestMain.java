package com.luckyrui.apitest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.luckyrui.exception.HttpClientException;
import com.luckyrui.utils.HttpClientUtil;

public class ApiTestMain {
	
	private static final String DEFAULT_HOST = "http://xxxx.cccc.com/";
	
	public static void main(String[] args) throws IOException, HttpClientException {
//		System.out.println(HttpClientUtil.get(DEFAULT_HOST+"android_api/anniversary/anniversaryNotice"));
		Map<String, String> params = new HashMap<String, String>();
		params.put("param1", "123456");
		params.put("pwd", "123456");
		System.out.println(HttpClientUtil.post(DEFAULT_HOST+"xxxx/xxxx/xxx" , params));
	}
}
