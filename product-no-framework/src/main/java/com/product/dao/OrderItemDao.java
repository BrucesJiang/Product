package com.product.dao;

import com.product.domain.Order;
import com.product.dto.OrderItem;
import com.product.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    //添加订单项
    public void addOrderItem(Order order) throws SQLException{
        List<OrderItem> orderItemList = order.getOrderItems();
        QueryRunner qr = new QueryRunner();
        String sql = "INSERT INTO orderitem VALUES(?, ?, ?)";
        Object[][] params = new Object[orderItemList.size()][];

        for(int i = 0; i < orderItemList.size(); i ++) {
            params[i] = new Object[]{order.getId(), orderItemList.get(i).getProduct().getId(), orderItemList.get(i).getBuynum()};
        }
        qr.batch(ManagerThreadLocal.getConnection(), sql, params);
    }
}
