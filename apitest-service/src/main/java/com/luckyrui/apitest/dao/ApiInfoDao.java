package com.luckyrui.apitest.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApiInfoDao extends SqliteBaseDao {

	@Override
	protected String getTableName() {
		return "at_api_info";
	}

	/**
	 * 添加接口
	 * 
	 * @param projectId
	 *            工程id
	 * @param apiName
	 *            接口名称
	 * @param apiUri
	 * @param apiMethod
	 * @param remark
	 * @author chenrui
	 * @date 2016年10月14日 下午3:44:42
	 * @version 201610
	 */
	public void addApiInfo(long projectId, String apiName, String apiUri, String apiMethod, String remark) {
		String sql = String.format(
				"insert into at_api_info(project_id, api_name, api_uri, api_method, ctime, remark) values('%d', '%s', '%s', '%s', '%tc', '%s')",
				projectId, apiName, apiUri, apiMethod, new Date(), remark);
		getSqlUtil().execute(sql);
	}

	/**
	 * 根据project查询接口列表
	 * 
	 * @param projectId
	 *            工程id
	 * @return
	 * @author chenrui
	 * @date 2016年10月14日 下午2:58:18
	 * @version 201610
	 */
	public List<Map<String, String>> queryAllByProjectId(long projectId) {
		List<Map<String, String>> rs = null;
		try {
			String sql = "select * from at_api_info where project_id = " + projectId + ";";
			rs = getSqlUtil().query(sql);
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
