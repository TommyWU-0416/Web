package com.awesome.dao;

import com.awesome.domain.User;

public interface UserDao {

    /**
     * 根據用戶名差尋用戶信息，查看使否存在此用戶
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用戶保存
     * @param user
     */
    public void save(User user);

    /**
     * 根據code查詢客戶
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新狀態
     * @param user
     */
    void updateStatus(User user);

    /**
     * 根據username 以及 password查詢用戶
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
