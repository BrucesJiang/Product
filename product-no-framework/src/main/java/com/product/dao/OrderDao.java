package com.product.dao;

import com.product.domain.Order;
import com.product.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class OrderDao {

    /**
     * 添加订单
     * @param order 订单
     */
    public void addOrder(Order order) throws SQLException{
        QueryRunner qr = new QueryRunner();
        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        qr.update(ManagerThreadLocal.getConnection(), sql, order.getId(), order.getMoney(), order.getReceiverAddress(),
                order.getReceiverName(), order.getReceiverPhone(), order.getPaystate(), order.getOrdertime(), order.getUser().getId());
    }
}
