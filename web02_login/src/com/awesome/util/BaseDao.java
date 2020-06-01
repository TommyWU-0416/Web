package com.awesome.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 吳awesome
 * *2020年5月29日下午2:56:22
 */
public class BaseDao {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql:///test";
	private static String user = "root";
	private static String password = "123";

	/**
	 * 數據庫連接方法
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName(driver); // 加載數據庫驅動
		System.out.println("測試加載數據庫成功");
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("測試數據庫鏈接成功");
		return con;
	}
	
	/**
	 * 關閉數據庫的方法
	 * @param con
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if( rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if( ps != null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if( con != null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean addUpdateDelete(String sql, Object[] arr) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = BaseDao.getCon();			// 1. 連接數據庫
			ps = con.prepareStatement(sql);	// 2. 編輯SQL
			
			// 3. 設置值
			if( arr != null && arr.length != 0) {
				for (int i = 0; i < arr.length; i++) {
					ps.setObject(i+1, arr[i]);
				}
			}
			int count = ps.executeUpdate();	// 4. 執行SQL
			if(count > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			BaseDao.getCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
