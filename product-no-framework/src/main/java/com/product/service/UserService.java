package com.product.service;

import com.product.domain.User;
import com.product.exception.UserException;

import java.sql.SQLException;

public interface UserService {

    /**
     * 用户注册
     *
     * @param user 输入参数为用户对象
     * @throws UserException 抛出运行时例外
     */
    public void register(User user) throws UserException;


    /**
     * 利用用户名和密码查找用户
     * @param username
     * @param password
     * @return user if not return  null
     * @throws UserException
     */
    public User findUser(String username, String password) throws UserException;


    /**
     * 激活用户
     * @param activeCode 激活码
     * @throws SQLException
     */
    public void activeUser(String activeCode) throws UserException;
}
