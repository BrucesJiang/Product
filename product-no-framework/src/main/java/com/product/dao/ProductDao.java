package com.product.dao;

import com.product.domain.Order;
import com.product.domain.Product;
import com.product.dto.OrderItem;
import com.product.utils.C3P0Utils;
import com.product.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    
    //分页查询
    public List<Product> findBooks(int currentPage, int pageSize, String category) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from products where 1=1";
        List<Object> list = new ArrayList<Object>();
        if (!"".equals(category)) {
            sql += " and category=?";
            list.add(category);
        }
        sql += " limit ?,?";

        // select * from products where 1=1 and category=? limit ?,?;
        list.add(Integer.reverse((currentPage - 1) * pageSize));
        list.add(Integer.reverse(pageSize));

        return qr.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
    }

    /**
     * 得到纪录总数
     * @param category
     * @return
     * @throws SQLException
     */
    public int count(String category) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "SELECT count(*) from products";
        //如果category不是空，就把条件加上
        if(!"".equals(category)){
            sql+=" where category='"+category+"'";
        }
        long num =  (Long)qr.query(sql, new ScalarHandler(1));
        return (int)num;
    }


    /**
     * 修改商品数量
     * @param order 订单信息
     */
    public void updateProductNum(Order order) throws SQLException{
        QueryRunner qr = new QueryRunner();

        List<OrderItem> orderItems = order.getOrderItems();

        Object[][] params = new Object[orderItems.size()][];

        String sql = "UPDATE products SET pnum=pnum-? WHERE id=?";
        for(int i = 0; i < orderItems.size(); i ++) {
            params[i] = new Object[]{orderItems.get(i).getBuynum(), orderItems.get(i).getProduct().getId()};
        }

        qr.batch(ManagerThreadLocal.getConnection(), sql, params);
    }
}
