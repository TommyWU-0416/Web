package com.awesome.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC的工具類
 * @author  吳思億
 * @version 創建時間：2020年1月21日 下午5:48:08
 * @since   JDK8.0
 */
public class JDBCUtils {
	// 創建一個連接詞，但是這連接詞只要創建一次即可
	private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 獲得連接方法
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	/**
	 * 獲得連接池
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
}
