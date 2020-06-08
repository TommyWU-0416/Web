package com.awesome.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.Test;

import com.alibaba.druid.util.JdbcUtils;
import com.awesome.dao.UserDao;
import com.awesome.domain.User;
import com.awesome.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			// 1.定義sql
			String sql = "select * from tab_user where username = ?";

			// 2.執行sql
			user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);

		} catch (Exception e) {

		}

		return user;
	}

	@Test
	public void test() {
		User user = new User(2, "bb", "abb", "cac", "2112-12-21", "南", "09123456789", "tom", "1", "12");
		if (user != null) {
			System.out.println("done");
		}
	}

	/**
	 * 保存用戶
	 */
	@Override
	public void save(User user) {

		// 1.定義sql
		String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
		// 2.執行sql
		try {
			queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(),
					user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根據激活碼查詢用戶對象
	 * 
	 * @param code
	 * @return
	 */
	@Override
	public User findByCode(String code) {
		User user = null;
		try {
			// 1.定義sql
			String sql = "select * from tab_user where code = ?";
			try {
				// 2.執行sql
				user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * 修改用戶激活狀態
	 * 
	 * @param user
	 */
	@Override
	public void updateStatus(User user) {
		// 1.定義sql
		String sql = " update tab_user set status = 'Y' where uid=?";
		try {
			// 2.執行sql
			queryRunner.update(sql, user.getUid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根據用戶名和密碼查詢的方法
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user = null;
		try {
			// 1.定義sql
			String sql = "select * from tab_user where username = ? and password = ?";
			
			// 2.執行sql
			user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (Exception e) {

		}

		return user;
	}
}
