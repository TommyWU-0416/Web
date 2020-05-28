package com.awesome.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
	1. 聲明靜態數據成員變量
	2. 創建連接池對象
	3. 定義公有得到數據源方法
	4. 定義得到連接對象的方法
	5. 定義關閉資源的方法
	6. 重載關閉方法
 */
public class JDBCUtils {
	// 1. 聲明靜態數據成員變量
	private static DataSource ds;

	// 2. 創建連接池對象
	static {
		// 加載配置文件中的數據
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
		Properties pp = new Properties();
		try {
			pp.load(is);
			// 創建連接池，使用配置文件中的參數
			ds = DruidDataSourceFactory.createDataSource(pp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 3. 定義公有得到數據源方法
	public static DataSource getDataSource() {
		return ds;
	}

	// 4. 定義得到連接對象的方法
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	// 5. 定義關閉資源的方法
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}

	// 6.重載關閉方法
	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
}
