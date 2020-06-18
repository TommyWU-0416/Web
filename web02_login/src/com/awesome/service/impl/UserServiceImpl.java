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
     * 註冊用戶
     *
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user, String path) {
        // 1.根據用戶名查詢用戶對象
        User u = userDao.findByUsername(user.getUsername());
        // 判斷u是否為null
        if (u != null) {
            // 用戶名存在，註冊失敗
            return false;
        }
        // 2.保存用戶信息
        // 2.1設置激活碼，並且唯一
        user.setCode(UuidUtil.getUuid());
        // 2.2設置激活狀態
        user.setStatus("N");
        userDao.save(user);

        // 3.激活郵件發送，郵件正文
        String content = "<a href='http://localhost:8080"+path+"/activeUserServlet?code=" + user.getCode() + "'>點擊激活【thirdshop】</a>";

        MailUtils.sendMail(user.getEmail(), content, "激活郵件");

        return true;
    }

    /**
     * 激活用戶
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        // 1.根據激活碼查詢用戶對象
        User user = userDao.findByCode(code);
        if (user != null) {
            // 2.調用dao修改激活狀態的方法
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 登錄方法
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
