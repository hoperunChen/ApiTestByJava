package com.luckyrui.apitest.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	
	public List<Map<String, String>> queryAll();
	
	public Map<String, String> queryById(long id);
}
