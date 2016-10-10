package com.luckyrui.apitest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ApiInfoDao extends SqliteBaseDao {

	@Override
	public List<Map<String, String>> queryAll() {
		List<Map<String, String>> rs = null;
		try {
			String sql = "select * from at_api_info;";
			rs = getSqlUtil().query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Map<String, String> queryById(long id) {
		Map<String, String> rs = null;
		try {
			String sql = "select * from at_api_info where id = " + id + ";";
			rs = getSqlUtil().queryOne(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) {
		ApiInfoDao aid = new ApiInfoDao();
		System.out.println(aid.queryById(1));
	}

}
