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
            String emailMsg = "注册成功，请<a href='http://www.product.com?activeCode="
                    + user.getActiveCode() + "'>激活</a>";
            SendEMail.sendMail(user.getEmail(), emailMsg);
        }catch(SQLException e){
            logger.error(e.getMessage(), e);
            throw new UserException("用户信息添加失败！");
        }
    }
}
