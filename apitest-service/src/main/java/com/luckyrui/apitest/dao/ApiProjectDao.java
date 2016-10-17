package com.luckyrui.apitest.dao;

import java.util.Date;

public class ApiProjectDao extends SqliteBaseDao {

	public static void main(String[] args) {
		ApiProjectDao aid = new ApiProjectDao();
		System.out.println(aid.queryById(1));
	}

	@Override
	protected String getTableName() {
		return "at_project";
	}

	/**
	 * 添加测试工程
	 * @param name 工程名
	 * @param domain 域名
	 * @param port 端口
	 * @param remark 备注(说明)
	 * @author chenrui
	 * @date 2016年10月14日 下午3:36:38
	 * @version 201610
	 */
	public void addProject(String name, String domain, int port, String remark) {
		String sql = String.format(
				"insert into at_project(name, domain, port, ctime, remark) values('%s', '%s', %d, '%tc','%s')", name,
				domain, port, new Date(), remark);
		getSqlUtil().execute(sql);
	}

}
