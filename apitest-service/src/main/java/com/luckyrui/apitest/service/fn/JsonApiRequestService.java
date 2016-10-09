package com.luckyrui.apitest.service.fn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luckyrui.apitest.service.entity.ApiRequestEntity;
import com.luckyrui.apitest.service.entity.Headers;
import com.luckyrui.apitest.service.entity.Params;
import com.luckyrui.utils.CheckUtil;
import com.luckyrui.utils.RandomUtil;
import com.luckyrui.utils.StringUtil;

/**
 * 解析json创建请求对象
 * 
 * @author chenrui
 * @date 2016年9月30日下午2:08:01
 * @version 201609
 */
public class JsonApiRequestService implements ApiRequestService{
	
	protected Map<String, ApiRequestEntity> requests = new HashMap<String, ApiRequestEntity>();
	
	private JSONArray ja;
	
	private RandomUtil randomUtil = RandomUtil.getInstance();

	public JsonApiRequestService(JSONArray ja) throws Exception {
		this.ja = ja;
		parseApi();
	}
	
	/**
	 * 解析api配置文件
	 * 
	 * @param ja
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年9月3日 下午5:37:06
	 * @version 2016_Anniversary
	 */
	protected void parseApi() throws Exception {
		if (CheckUtil.isEmpty(ja)) {
			log.error("方法入参:ja不能为空");
		}
		String _temp = "";
		log.info("开始解析配置" + ja.toJSONString());
		Iterator<Object> it = ja.iterator();
		while (it.hasNext()) {
			JSONObject jsonApiBean = (JSONObject) it.next();
			String name = jsonApiBean.getString("name");
			String uri = jsonApiBean.getString("uri");
			String method = jsonApiBean.getString("method");
			if (CheckUtil.isEmpty(name, uri, method)) {
				log.error("解析失败,配置缺少必须参数:" + jsonApiBean);
				throw new Exception("解析失败,配置缺少必须参数:" + jsonApiBean);
			}
			int methodI = method.trim().toUpperCase().equals("GET") ? ApiRequestEntity.REQUEST_METHOD_GET
					: ApiRequestEntity.REQUEST_METHOD_POST;
			String headers = StringUtil.ifnull(jsonApiBean.getString("header"), "");
			String params = StringUtil.ifnull(jsonApiBean.getString("params"), "");
			String port = StringUtil.ifnull(jsonApiBean.getString("port"), "");
			// 判断重复
			if (_temp.contains(name)) {
				log.error(name + "接口重复配置");
				continue;
			}
			_temp += (name);
			String id = randomUtil.genString(4, 2);
			requests.put(id,new ApiRequestEntity(Long.parseLong(id),name, uri, port, methodI, parseHeaders(headers), parseParams(params)));
		}
	}
	
	private Headers parseHeaders(String header) {
		if (CheckUtil.isEmpty(header)) {
			return null;
		}
		JSONObject headerJson = (JSONObject) JSON.parse(header);
		Headers headers = new Headers(headerJson.toJSONString());
		return headers;
	}

	private Params parseParams(String paramStr) throws Exception {
		if (CheckUtil.isEmpty(paramStr)) {
			return null;
		}
		Params params = Params.getParamsByParamsStr(paramStr);
		return params;
	}
	
	/**
	 * 显示api列表
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年9月3日 下午5:38:23
	 * @version 2016_Anniversary
	 */
	public String showApiList() {
		StringBuffer rtn = new StringBuffer();
		for (String key : requests.keySet()) {
			rtn.append(key + ":" + requests.get(key).toString() + "\n");
		}
		return rtn.toString();
	}

	public void request(String key) {
		requests.get(key).sendRequest();
	}
	

}
