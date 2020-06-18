package com.awesome.service;

import com.awesome.domain.User;

public interface UserService {
    /**
     * 註冊用戶
     * @param user
     * @param path 
     * @return
     */
    boolean regist(User user, String path);

    boolean active(String code);

    User login(User user);
}
