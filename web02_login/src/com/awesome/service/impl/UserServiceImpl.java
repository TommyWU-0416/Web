package com.awesome.service.impl;

import com.awesome.dao.UserDao;
import com.awesome.dao.impl.UserDaoImpl;
import com.awesome.domain.User;
import com.awesome.service.UserService;
import com.awesome.util.MailUtils;
import com.awesome.util.UuidUtil;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean regist(User user) {
		// 1.根据用户名查询用户对象
		System.out.println("regist");
		User u = userDao.findByUsername(user.getUsername());
		// 判断u是否为null
		if (u != null) {
			// 用户名存在，注册失败
			return false;
		}
		// 2.保存用户信息
		// 2.1设置激活码，唯一字符串
//		user.setCode(UuidUtil.getUuid());
//		// 2.2设置激活状态
//		user.setStatus("N");
//		userDao.save(user);
//
//		// 3.激活邮件发送，邮件正文？
//
//		String content = "<a href='http://localhost:8080/travel/activeUserServlet?code=" + user.getCode()
//				+ "'>點擊激活【黑馬旅遊網】</a>";
//
//		MailUtils.sendMail(user.getEmail(), content, "激活郵件");

		return true;
	}

	/**
	 * 激活用户
	 * 
	 * @param code
	 * @return
	 */
	@Override
	public boolean active(String code) {
		// 1.根据激活码查询用户对象
		User user = userDao.findByCode(code);
		if (user != null) {
			// 2.调用dao的修改激活状态的方法
			userDao.updateStatus(user);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 登录方法
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public User login(User user) {
		return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
