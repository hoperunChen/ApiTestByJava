package com.luckyrui.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqliteDBUtils {

	private Log logger = LogFactory.getLog(SqliteDBUtils.class);

	private static String dbPath = null;
	
	private FileUtil fu = new FileUtil();

	private static class SqliteDBUtilsHandler {
		private static SqliteDBUtils instance = new SqliteDBUtils();
	}

	private SqliteDBUtils() {
		init(fu.getProjectPath(),"apitest.sqlite");
	}

	public static SqliteDBUtils getInstance() {
		return SqliteDBUtilsHandler.instance;
	}

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

	public Connection getConnection() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

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

	public ResultSet query(String sql, Statement statement) {
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sql);
			logger.info("query SQL:" + sql);
		} catch (SQLException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error(sw.toString());
			logger.error("【" + sql + "】");
		}
		return rs;

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
