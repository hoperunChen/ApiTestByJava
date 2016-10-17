package com.luckyrui.apitest.service.fn;

import java.util.List;
import java.util.Map;

import com.luckyrui.apitest.dao.ApiInfoDao;
import com.luckyrui.utils.StringUtil;

public class DbApiRequestService {
	
	ApiInfoDao apiInfoDao = new ApiInfoDao();

	public void request(String id) {
		// TODO Auto-generated method stub
	}

	public List<Map<String, Object>> showApiList(long projectId) {
		List<Map<String, String>> apiInfos = apiInfoDao.queryAllByProjectId(projectId);
		
		return null;
	}
	
	public List<Map<String, Object>> showApiProject(long projectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public long addProject(String projectName,String domain,String prot,String remark) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public long addApi(long projectId,String apiName,String apiUri,String api_method,String remark) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	

}
