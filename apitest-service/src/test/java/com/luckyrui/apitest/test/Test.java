package com.luckyrui.apitest.test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.luckyrui.apitest.service.entity.ApiRequestEntity;
import com.luckyrui.apitest.service.entity.Params;
import com.luckyrui.utils.EncryptUtil;

public class Test {
	private static final String BASE_URI_DEV = "http://dev.duomeidai.com/";
	private static final String BASE_URI_LOCAL = "http://localhost:8080/";

	public static void main(String[] args) throws UnsupportedEncodingException {
		getNewToken(BASE_URI_DEV);
	}

	private static void login(String baseurl) {
		Params p = new Params();
		p.addParams("phoneNumber", "18612372114");
		p.addParams("loginPassword", "123456");
		// p.addParams("deviceUUid", "hellos_jsisjsiskd");
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "android_api/login", p);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();
	}

	private static void takePrize(String baseurl) {
		String deviceUUid = "hellos_jsisjsiskd";
		String userToken = "4F5D15B53DEB53EF753CAE13F41AB9EF";
		String signToken = "05f8521cde6fc61dd0750eff27854101";
		long timestmp = new Date().getTime();
		String accessToken = "dmd" + signToken + timestmp + "duomeidai";
		accessToken = EncryptUtil.md5(accessToken);
		// userToken = URLEncoder.encode(userToken,"UTF-8");
		Params p = new Params();
		p.addParams("token", userToken);
		// p.addParams("accessToken", accessToken);
		// p.addParams("timeStmp", timestmp + "");
		// p.addParams("deviceUUid", deviceUUid);
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "android_api/user/anniversary/takePrize", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	private static void getNewToken(String baseurl) {
		String deviceUUid = "5ADA5C66-ED97-46F7-B3EA-88F45BF60D4B";
		String userToken = "50503A5F9EBE56CD625622C0EFCFD1D54B470A738BB5DF7C";
		long timestmp = new Date().getTime();
		String accessToken = "";
		Params p = new Params();
		p.addParams("token", userToken);
		 p.addParams("accessToken", accessToken);
		 p.addParams("timeStmp", timestmp + "");
		 p.addParams("deviceUUid", deviceUUid);
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "/api/get_new_token", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();
	}

}
