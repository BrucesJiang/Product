package com.product.service.impl;

import com.product.dao.UserDao;
import com.product.domain.User;
import com.product.exception.UserException;
import com.product.service.UserService;
import com.product.utils.SendEMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDao();
    @Override
    public void register(User user) throws UserException {
        try{
            userDao.addUser(user);
            String emailMsg = "注册成功，请<a href='http://www.product.com/user?method=active&activeCode="
                    + user.getActiveCode() + "'>激活</a>";
            SendEMail.sendMail(user.getEmail(), emailMsg);
        }catch(SQLException e){
            logger.error(e.getMessage(), e);
            throw new UserException("用户信息添加失败！");
        }
    }


    @Override
    public User findUser(String username, String password) throws UserException{
        try{
            return userDao.findUser(username, password);
        }catch(SQLException e){
            logger.error(e.getMessage(), e);
            throw new UserException("用户名或密码错误，或者您没有激活账号？");
        }
    }

    @Override
    public void activeUser(String activeCode) throws UserException {
        try{
            userDao.activeUser(activeCode);
        }catch(SQLException e){
            logger.error(e.getMessage(), e);
            throw new UserException("激活用户失败，请重新激活！");
        }
    }
}
