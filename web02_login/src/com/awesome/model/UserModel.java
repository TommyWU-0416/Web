package com.awesome.model;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.awesome.domain.User;
import com.awesome.utils.JDBCUtils;

/**
 * 處理數據的JavaBean
 * 
 * @author 吳思億
 * @version 創建時間：2020年1月21日 下午5:48:08
 * @since JDK8.0
 */
public class UserModel {
	/**
	 * 處理用戶數據的方法
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User login(User user) throws SQLException {
		// 連接數據庫，通過傳入用戶名和密碼去數據庫中進行查詢
		System.out.println("123");
		
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		// query(sql, rsh, params);
		User existUser = queryRunner.query("select * from user where username = ? and password = ?",
				new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());	
		System.out.println(456);
		return existUser;
	}

}
