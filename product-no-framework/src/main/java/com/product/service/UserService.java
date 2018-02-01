package com.product.service;

import com.product.domain.User;
import com.product.exception.UserException;

public interface UserService {

    /**
     * 用户注册
     *
     * @param user 输入参数为用户对象
     * @throws UserException 抛出运行时例外
     */
    public void register(User user) throws UserException;


}
