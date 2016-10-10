package com.luckyrui.apitest.dao;

import com.luckyrui.utils.SqliteDBUtils;

public abstract class SqliteBaseDao implements BaseDao{
	
	/**
	 * 获取SqliteDBUtils
	 * @return
	 * @author chenrui
	 * @date 2016年10月10日 上午11:11:30
	 * @version 201610
	 */
	protected SqliteDBUtils getSqlUtil(){
		return SqliteDBUtils.getInstance();
	}
}
