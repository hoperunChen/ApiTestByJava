package com.luckyrui.apitest.service.fn;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luckyrui.apitest.service.entity.ApiRequestEntity;

public abstract class ApiRequestService {
	static Log log = LogFactory.getLog("ApiRequestFn.class");
	protected Map<String, ApiRequestEntity> requests = new HashMap<String, ApiRequestEntity>();

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

	/**
	 * 解析api配置文件
	 * 
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年9月3日 下午5:37:06
	 * @version 2016_Anniversary
	 */
	protected abstract void parseApi() throws Exception;

}
