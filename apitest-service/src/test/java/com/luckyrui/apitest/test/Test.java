package com.luckyrui.apitest.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import com.luckyrui.apitest.service.entity.ApiRequestEntity;
import com.luckyrui.apitest.service.entity.Params;
import com.luckyrui.utils.EncryptUtil;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
//		takePrize();
		login();
//		System.out.println(new Date().getTime());
	}
	
	private static void login(){
		Params p = new Params();
		p.addParams("phoneNumber", "18612372114");
		p.addParams("loginPassword", "123456");
		p.addParams("deviceUUid", "hellos_jsisjsiskd");
		ApiRequestEntity are = new ApiRequestEntity("http://dev.duomeidai.com/android_api/login", p);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();
	}
	
	private static void takePrize(){
		String deviceUUid = "hellos_jsisjsiskd";
		String userToken = "4F5D15B53DEB53EF753CAE13F41AB9EF";
		String signToken = "05f8521cde6fc61dd0750eff27854101";
		long timestmp = new Date().getTime();
		String accessToken = "dmd" + signToken + timestmp + "duomeidai";
		accessToken = EncryptUtil.md5(accessToken);
//		userToken = URLEncoder.encode(userToken,"UTF-8");
		Params p = new Params();
		p.addParams("token", userToken);
		p.addParams("accessToken", accessToken);
		p.addParams("timeStmp", timestmp + "");
		p.addParams("deviceUUid", deviceUUid);
		ApiRequestEntity are = new ApiRequestEntity("http://dev.duomeidai.com/android_api/user/anniversary/takePrize", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();
	
	}

	
}
