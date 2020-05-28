package com.awesome.service;

import com.awesome.domain.User;

public interface UserService {
    /**
     * 註冊用戶
     * @param user
     * @return
     */
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
