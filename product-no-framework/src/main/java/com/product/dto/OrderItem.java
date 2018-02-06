package com.product.dto;

import com.product.domain.Order;
import com.product.domain.Product;

public class OrderItem {

    private Order order; //订单
    private Product product; //商品
    private int buynum; //购买数量

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }
}
