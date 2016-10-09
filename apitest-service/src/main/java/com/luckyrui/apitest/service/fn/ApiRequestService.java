package com.luckyrui.apitest.service.fn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface ApiRequestService {
	static Log log = LogFactory.getLog("ApiRequestFn.class");

	/**
	 * 显示api列表
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年9月3日 下午5:38:23
	 * @version 2016_Anniversary
	 */
	public String showApiList();

	public void request(String key);



}
