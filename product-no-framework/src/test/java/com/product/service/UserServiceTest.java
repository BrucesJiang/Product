package com.product.service;

import com.product.domain.User;
import com.product.service.impl.UserServiceImpl;
import com.product.utils.MD5Utils;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService us = new UserServiceImpl();
    User user = new User();

    @Test
    public void activeUser() throws Exception {
        String activeCode = "a497b19a-4123-4043-b3ba-ee166909753c";
        us.activeUser(activeCode);
    }


    @Test
    public void register() throws Exception {
        user.setUsername("Cool");
        user.setPassword(MD5Utils.md5("123456"));
        user.setTelephone("13043266625");
        user.setIntroduce("哈哈哈");
        user.setEmail("bruce@163.com");
        user.setGender("男");
        user.setActiveCode(UUID.randomUUID().toString());
        us.register(user);
    }

    @Test
    public void findUser() throws Exception {
        user = us.findUser("Cool", MD5Utils.md5("123456"));
        System.out.println(user.toString());
    }

}