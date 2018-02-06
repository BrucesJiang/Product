package com.product.service.impl;

import com.product.dao.OrderDao;
import com.product.dao.OrderItemDao;
import com.product.dao.ProductDao;
import com.product.domain.Order;
import com.product.exception.OrderException;
import com.product.service.OrderService;
import com.product.utils.ManagerThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    OrderDao orderDao = new OrderDao();
    OrderItemDao orderItemDao = new OrderItemDao();
    ProductDao productDao = new ProductDao();

    @Override
    public void addOrder(Order order) throws OrderException {
        try{
            ManagerThreadLocal.startTransaction(); //开启事务，设置手动提交
            addOrder(order);
            orderDao.addOrder(order);
            orderItemDao.addOrderItem(order);
            productDao.updateProductNum(order);
            ManagerThreadLocal.commit(); //手动提交事务
        }catch(SQLException e) {
            logger.error(e.getMessage(), e);
            ManagerThreadLocal.rollback(); //回滚事务
        }
    }
}
