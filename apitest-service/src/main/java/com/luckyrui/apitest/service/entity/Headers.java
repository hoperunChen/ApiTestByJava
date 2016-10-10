package com.luckyrui.apitest.service.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.luckyrui.utils.CheckUtil;

/**
 * 请求头
 * 
 * @author chenrui
 * @date 2016年8月30日上午10:32:55
 * @version persistence
 */
public class Headers extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9173762164897003692L;

	private class HeadersMap {
		private ConcurrentMap<String, Header> headers = new ConcurrentHashMap<String, Header>();

		public HeadersMap() {

		}

		public HeadersMap(Map<String, String> map) {
			for (String key : map.keySet()) {
				Header p = new Header(key, map.get(key));
				headers.put(key, p);
			}
		}

		public void put(String key, String value) {
			Header header = new Header(key, value);
			headers.put(key, header);
		}

		public void remove(String key) {
			headers.remove(key);
		}

		public String get(String key) {
			Header header = headers.get(key);
			return header.getValue();
		}

		public Map<String, String> getHeaders() {
			Map<String, String> rtn = new HashMap<String, String>();
			for (String key : headers.keySet()) {
				rtn.put(key, headers.get(key).getValue());
			}
			return rtn;
		}

		public Set<String> keySet() {
			return headers.keySet();
		}

	}

	private HeadersMap headersMap;

	public Headers() {
		headersMap = new HeadersMap();
	}

	/**
	 * 使用json字符串创建header
	 * 
	 * @param headerStr
	 *            jsonString
	 */
	public Headers(String headerStr) {
		try {
			JSONObject jo = (JSONObject) JSON.parse(headerStr);
			for (String key : jo.keySet()) {
				headersMap.put(key, jo.getString(key));
			}
		} catch (JSONException e) {
			throw new JSONException(" " + headerStr + "  is not a json string");
		}
	}

	/**
	 * 通过map创建header
	 * 
	 * @param map
	 */
	public Headers(Map<String, String> map) {
		headersMap = new HeadersMap(map);
	}

	/**
	 * 添加header
	 * 
	 * @param key
	 * @param value
	 * @author chenrui
	 * @date 2016年8月30日 下午12:04:47
	 * @version persistence
	 */
	public void addHeader(String key, String value) {
		if (CheckUtil.isEmpty(key, value))
			throw new NullPointerException("key and value can not be null");
		if (headersMap == null) {
			headersMap = new HeadersMap();
		}
		headersMap.put(key.trim(), value.trim());
	}

	/**
	 * 删除header
	 * 
	 * @param key
	 * @author chenrui
	 * @date 2016年8月30日 下午12:25:02
	 * @version persistence
	 */
	public void removeHeader(String key) {
		if (CheckUtil.isEmpty(key))
			return;
		if (headersMap == null) {
			headersMap = new HeadersMap();
		}
		headersMap.remove(key);
	}

	/**
	 * 获取header
	 * 
	 * @param key
	 * @author chenrui
	 * @date 2016年8月30日 下午12:25:02
	 * @version persistence
	 */
	public String getHeader(String key) {
		if (CheckUtil.isEmpty(key))
			return null;
		if (headersMap == null) {
			headersMap = new HeadersMap();
			return null;
		}
		return headersMap.get(key);
	}

	/**
	 * 获取headers
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午10:02:55
	 * @version 2016_Anniversary
	 */
	public Map<String, String> getHeaders() {
		return headersMap.getHeaders();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<String> keys = headersMap.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			sb.append(key + ":" + headersMap.get(key));
			sb.append(keys.hasNext() ? "," : "");
		}
		return sb.toString();
	}

}
