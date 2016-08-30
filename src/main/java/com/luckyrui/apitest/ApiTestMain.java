package com.luckyrui.apitest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.luckyrui.apitest.persistence.entity.ApiRequestEntity;
import com.luckyrui.apitest.persistence.entity.Headers;
import com.luckyrui.apitest.persistence.entity.Params;
import com.luckyrui.exception.HttpClientException;
import com.luckyrui.utils.HttpClientUtil;

public class ApiTestMain {
	
	private static final String DEFAULT_HOST = "http://xxxx.cccc.com/";
	
	public static void main(String[] args) throws IOException, HttpClientException {
//		System.out.println(HttpClientUtil.get(DEFAULT_HOST+"android_api/anniversary/anniversaryNotice"));
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("param1", "123456");
//		params.put("pwd", "123456");
//		System.out.println(HttpClientUtil.post(DEFAULT_HOST+"xxxx/xxxx/xxx" , params));
		
		Params p = new Params();
		p.addParams("token", "2029C8BD72ABB377D003C76DF28959143A4BC88323F3F172");
//		HttpClientUtil.get("http://dev.duomeidai.com/android_api/anniversary/anniversaryPageList"+p.getGetParams());
		
		
		ApiRequestEntity e = new ApiRequestEntity("http://dev.duomeidai.com/android_api/anniversary/anniversaryPageList", p);
		System.out.println(e.sendRequest());
	}
}
