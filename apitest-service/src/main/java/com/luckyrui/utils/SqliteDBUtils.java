package com.luckyrui.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * sqlite工具类
 * 
 * @author chenrui
 * @date 2016年10月10日下午3:21:03
 * @version 201610
 */
public class SqliteDBUtils {

	private Log logger = LogFactory.getLog(SqliteDBUtils.class);

	private static String dbPath = null;

	private FileUtil fu = new FileUtil();

	private static class SqliteDBUtilsHandler {
		private static SqliteDBUtils instance = new SqliteDBUtils();
	}

	private SqliteDBUtils() {
		init(fu.getProjectPath(), "apitest.db");
	}

	public static SqliteDBUtils getInstance() {
		return SqliteDBUtilsHandler.instance;
	}

	/**
	 * 初始化
	 * @param dataDir
	 * @param dbName
	 * @author chenrui
	 * @date 2016年10月10日 下午3:39:09
	 * @version 201610
	 */
	public synchronized void init(String dataDir, String dbName) {
		dbPath = dataDir + dbName;
		File dbDir = new File(dataDir);
		if (!dbDir.exists()) {
			dbDir.mkdirs();
		}
		logger.info("generate db file:" + dbPath);
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {

			e.printStackTrace();
		}

		File dbFile = new File(dbPath);
		if (!dbFile.exists()) {
			try {
				dbFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getDBPath() {

		return dbPath;
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @author chenrui
	 * @date 2016年10月10日 下午3:39:56
	 * @version 201610
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 批量执行语句
	 * @param sqlList
	 * @author chenrui
	 * @date 2016年10月10日 下午3:44:42
	 * @version 201610
	 */
	public void execute(List<String> sqlList) {
		try {
			Connection conn = this.getConnection();
			if (conn == null)
				return;
			Statement statement = conn.createStatement();
			for (String sql : sqlList) {
				execute(sql, statement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 执行sql语句
	 * @param sql
	 * @author chenrui
	 * @date 2016年10月10日 下午3:44:54
	 * @version 201610
	 */
	public void execute(String sql) {
		try {
			Connection conn = this.getConnection();
			if (conn == null)
				return;
			Statement statement = conn.createStatement();
			execute(sql, statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行sql语句
	 * @param sql
	 * @param statement
	 * @author chenrui
	 * @date 2016年10月10日 下午3:45:07
	 * @version 201610
	 */
	public void execute(String sql, Statement statement) {
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			logger.error(sw.toString());

			logger.error("【" + sql + "】");
		}

	}

	/**
	 * 查询
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @author chenrui
	 * @date 2016年10月10日 下午3:45:16
	 * @version 201610
	 */
	public List<Map<String, String>> query(String sql) throws SQLException {
		List<Map<String, String>> data = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			data = resultToList(rs);
			logger.info("query SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("【" + e.getMessage() + "】");
		} finally {
			rs.close();
			ps.close();
			conn.close();
		}
		return data;
	}

	/**
	 * 查询一条数据
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @author chenrui
	 * @date 2016年10月10日 下午3:53:18
	 * @version 201610
	 */
	public Map<String, String> queryOne(String sql) throws SQLException {

		Map<String, String> data = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			data = resultToMap(rs);
			logger.info("query SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("【" + e.getMessage() + "】");
		} finally {
			rs.close();
			ps.close();
			conn.close();
		}
		return data;

	}

	/**
	 * 将结果转换为list
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @author chenrui
	 * @date 2016年10月10日 下午3:54:45
	 * @version 201610
	 */
	private List<Map<String, String>> resultToList(ResultSet rs) throws SQLException {
		List<Map<String, String>> datas = null;
		String[] colNames = getColNames(rs);
		datas = new ArrayList<Map<String, String>>();
		while (rs.next()) {
			Map<String, String> rowData = new HashMap<String, String>();
			for (String colName : colNames) {
				rowData.put(colName, rs.getString(colName));
			}
			datas.add(rowData);
			rowData = null;
		}
		return datas;
	}

	/**
	 * 将结果转换为map
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @author chenrui
	 * @date 2016年10月10日 下午3:54:59
	 * @version 201610
	 */
	private Map<String, String> resultToMap(ResultSet rs) throws SQLException {
		Map<String, String> data = null;
		String[] colNames = getColNames(rs);
		if (rs.next()) {
			data = new HashMap<String, String>();
			for (String colName : colNames) {
				data.put(colName, rs.getString(colName));
			}
		}
		return data;
	}

	/**
	 * 获取所有列名
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @author chenrui
	 * @date 2016年10月10日 下午3:55:12
	 * @version 201610
	 */
	private String[] getColNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int colcount = rsmd.getColumnCount();
		String[] colNames = new String[colcount];
		for (int i = 0; i < colcount; i++) {
			colNames[i] = rsmd.getColumnName(i + 1);
		}
		return colNames;
	}

	public void close() {
		close(this.getConnection());
	}

	public void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
