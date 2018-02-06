package com.product.service;

import com.product.domain.Order;
import com.product.exception.OrderException;

public interface OrderService {

    /**
     * 添加订单
     * @param order
     * @throws OrderException
     */
    public void addOrder(Order order) throws OrderException;
}
