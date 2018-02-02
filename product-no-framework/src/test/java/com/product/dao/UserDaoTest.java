package com.product.dao;

import com.product.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao ud = new UserDao();
    @Test
    public void addUser() throws Exception {
    }

    @Test
    public void findUser() throws Exception {
        User user = ud.findUser("Bruce", "123456");
        System.out.println(user.toString());
    }

}