package com.product.dao;

import com.product.domain.User;
import com.product.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {

    public void addUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "INSERT INTO user(username,password,gender,email,telephone,introduce,activeCode,state) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        qr.insert(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword(), user.getGender(),
                user.getEmail(), user.getTelephone(), user.getIntroduce(), user.getActiveCode(), user.getState());
    }
}
