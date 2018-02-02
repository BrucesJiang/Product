package com.product.dao;

import com.product.domain.User;
import com.product.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
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

    public User findUser(String username, String password) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND state = 1";
        return qr.query(sql, new BeanHandler<User>(User.class), username, password);
    }


    public void activeUser(String activeCode) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE user SET state = 1 WHERE activeCode = ?";
        qr.update(sql, activeCode);
    }

    public User findUserById(String id) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT * FROM user WHERE id = ? AND state = 1";
        return qr.query(sql, new BeanHandler<User>(User.class), id);
    }

    public void updateUserInfo(User user) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE user SET username = ?, password = ?, gender = ?, telephone = ? WHERE id = ? AND state = 1";
        qr.update(sql, user.getUsername(), user.getPassword(), user.getGender(), user.getTelephone(), user.getId());
    }
}
