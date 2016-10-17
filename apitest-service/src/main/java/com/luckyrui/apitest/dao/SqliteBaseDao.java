package com.luckyrui.apitest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.luckyrui.utils.SqliteDBUtils;

public abstract class SqliteBaseDao implements BaseDao {
	/**
	 * 表名
	 */
	protected String tableName = null;

	public SqliteBaseDao() {
		tableName = getTableName();
	}

	/**
	 * 设置表名
	 * @return
	 * @author chenrui
	 * @date 2016年10月14日 下午3:27:15
	 * @version 201610
	 */
	protected abstract String getTableName();

	/**
	 * 获取SqliteDBUtils
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年10月10日 上午11:11:30
	 * @version 201610
	 */
	protected SqliteDBUtils getSqlUtil() {
		return SqliteDBUtils.getInstance();
	}

	@Override
	public List<Map<String, String>> queryAll() {
		List<Map<String, String>> rs = null;
		try {
			String sql = "select * from " + tableName + ";";
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
			String sql = "select * from " + tableName + " where id = " + id + ";";
			rs = getSqlUtil().queryOne(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
