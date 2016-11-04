package com.luckyrui.apitest.test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;
import com.luckyrui.apitest.service.entity.ApiRequestEntity;
import com.luckyrui.apitest.service.entity.Params;
import com.luckyrui.base.callback.CallbackAble;
import com.luckyrui.utils.EncryptUtil;
import com.luckyrui.utils.StringUtil;

public class Test {
	private static final String BASE_URI_DEV = "http://dev.duomeidai.com/";
	private static final String BASE_URI_WWW = "http://www.duomeidai.com/";
	private static final String BASE_URI_LOCAL = "http://localhost:8080/";

	private static String deviceUUid = "hellos_jsisjsiskd";
	private static String userToken;
	private static String signToken;

	public static void main(String[] args) throws UnsupportedEncodingException {
		boolean flag = true;
		Scanner in = new Scanner(System.in);
		String userInput = "";
		String env = BASE_URI_LOCAL;
		while (flag) {
			System.out.println("请选择:");
			System.out.println("\n\t 0:获取token(登陆)");
			System.out.println("\n\t 1:初始化邀请页面_old");
			System.out.println("\n\t 2:初始化邀请页面_new");
			System.out.println("\n\t 3:提取佣金_new");
			System.out.println("\n\t 4:初始化邀请详情页_new");
			System.out.println("\n\t 5:初始化佣金详情页_new");
			System.out.println("\n\t 6:初始化新手任务页_new");
			System.out.println("\n\t 7:领取新手任务奖励_new");
			System.out.println("\n\t 8:完成新手任务_new");
			userInput = in.next();
			switch (userInput) {
			case "0":
				login(env);
				break;
			case "1":
				initCommissionOld(env);
				break;
			case "2":
				initCommissionNew(env);
				break;
			case "3":
				takeCommissionNew(env);
				break;
			case "4":
				initInviteDetail(env);
				break;
			case "5":
				initcommDetail(env);
				break;
			case "6":
				initNewTask(env);
				break;
			case "7":
				initNewTaskPrize(env);
				break;
			case "8":
				completeNewTask(env);
				break;
			case "exit":
				flag = false;
				break;
			default:
				break;
			}
		}
		in.close();
		System.exit(0);
	}

	private static Params getTokenParams() {
		Params p = new Params();
		long timestmp = new Date().getTime();
		if (StringUtil.isNotBlank(signToken)) {
			String accessToken = "dmd" + signToken + timestmp + "duomeidai";
			accessToken = EncryptUtil.md5(accessToken);

			p.addParams("token", userToken);
			p.addParams("accessToken", accessToken);
		}
		p.addParams("timeStmp", timestmp + "");
		p.addParams("deviceUUid", deviceUUid);
		return p;
	}

	/**
	 * 登陆(获取token)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 上午11:22:10
	 * @version 201611
	 */
	private static void login(String baseurl) {
		Params p = new Params();
		p.addParams("phoneNumber", "18612372114");
		p.addParams("loginPassword", "123456");
		p.addParams("deviceUUid", deviceUUid);
		// p.addParams("deviceUUid", "hellos_jsisjsiskd");
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "api/login", p);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest(new CallbackAble<String>() {
			@Override
			public void call(String data) {
				JSONObject jo = JSONObject.parseObject(data);
				String _token = jo.getString("token");
				userToken = _token.substring(0, _token.indexOf("DMD,"));
				signToken = _token.substring(_token.indexOf("DMD,") + 4);
				System.out.println(userToken + "," + signToken);
			}
		});
	}

	/**
	 * 初始化佣金
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:27
	 * @version 201611
	 */
	private static void initCommissionOld(String baseurl) {
		Params p = getTokenParams();
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "api/commission/invite/init", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 初始化佣金(新)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void initCommissionNew(String baseurl) {
		Params p = getTokenParams();
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/commssion/initPage", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 领取佣金(新)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void takeCommissionNew(String baseurl) {
		Params p = getTokenParams();
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/user/commission/take", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 初始化邀请详情页(新)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void initInviteDetail(String baseurl) {
		Params p = getTokenParams();
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/user/commission/invite_detail/init", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 初始化佣金详情页(新)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void initcommDetail(String baseurl) {
		Params p = getTokenParams();
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/user/commission/comm_detail/init", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 初始化新手任务(新)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void initNewTask(String baseurl) {
		Params p = getTokenParams();
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/new_task/initPage", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 领取新手任务奖励(新)
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void initNewTaskPrize(String baseurl) {
		Params p = getTokenParams();
		p.addParams("prizeType", "1");
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/user/new_task/getNewtaskPrize", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

	/**
	 * 完成新手任务
	 * 
	 * @param baseurl
	 * @author chenrui
	 * @date 2016年11月3日 下午2:28:42
	 * @version 201611
	 */
	private static void completeNewTask(String baseurl) {
		Params p = getTokenParams();
		p.addParams("taskid", "1");
		ApiRequestEntity are = new ApiRequestEntity(baseurl + "v3/user/new_task/completeNewTask", p);
		System.out.println(userToken);
		are.setMethod(ApiRequestEntity.REQUEST_METHOD_POST);
		are.sendRequest();

	}

}
