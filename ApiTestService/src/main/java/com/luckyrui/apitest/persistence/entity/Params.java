package com.luckyrui.apitest.persistence.entity;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.luckyrui.utils.CheckUtil;

/**
 * 请求参数
 * 
 * @author chenrui
 * @date 2016年8月30日下午12:27:28
 * @version persistence
 */
public class Params extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1457883818930454711L;

	private ConcurrentMap<String, String> paramsMap = new ConcurrentHashMap<String, String>();

	public Params() {

	}

	/**
	 * 通过map创建header
	 * 
	 * @param map
	 */
	public Params(Map<String, String> map) {
		paramsMap = new ConcurrentHashMap<>(map);
	}

	/**
	 * 通过json创建params
	 * 
	 * @param jsonStr
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午3:49:13
	 * @version 2016_Anniversary
	 */
	public static Params getParamsByJson(String jsonStr) {
		Params p = null;
		try {
			JSONObject jo = (JSONObject) JSON.parse(jsonStr);
			p = new Params();
			for (String key : jo.keySet()) {
				p.addParams(key, jo.getString(key));
			}
		} catch (JSONException e) {
			throw new JSONException(" " + jsonStr + "  is not a json string");
		}
		return p;
	}

	/**
	 * 通过参数字符串创建params
	 * 
	 * @param paramsStr
	 * @return
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年8月30日 下午9:20:32
	 * @version persistence
	 */
	public static Params getParamsByParamsStr(String paramsStr) throws Exception {
		if (CheckUtil.isEmpty(paramsStr)) {
			throw new NullPointerException(paramsStr + " can not be null");
		}
		Params p = new Params();
		String[] _params = new String[] { paramsStr };
		if (paramsStr.indexOf('&') > 0) {
			_params = paramsStr.split("&");
		}
		for (String __p : _params) {
			if (__p.indexOf("=") < 0) {
				throw new Exception("params not exists =");
			}
			String key = __p.substring(0, __p.indexOf("="));
			String value = __p.substring(__p.indexOf("=") + 1);
			p.addParams(key, value);
		}
		return p;
	}

	/**
	 * 添加参数
	 * 
	 * @param key
	 * @param value
	 * @author chenrui
	 * @date 2016年8月30日 下午12:31:45
	 * @version persistence
	 */
	public void addParams(String key, String value) {
		paramsMap.put(key, value);
	}

	/**
	 * 获取get请求参数
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午9:23:38
	 * @version persistence
	 */
	public String getGetParams() {
		String rtn = "?";
		for (String key : paramsMap.keySet()) {
			rtn += key + "=" + paramsMap.get(key);
		}
		return rtn;
	}

	/**
	 * 获取post请求参数
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午9:24:44
	 * @version persistence
	 */
	public Map<String, String> getPostParams() {
		return paramsMap;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<String> keys = paramsMap.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			sb.append(key + ":" + paramsMap.get(key));
			sb.append(keys.hasNext() ? "," : "");
		}
		return sb.toString();

	}

}
