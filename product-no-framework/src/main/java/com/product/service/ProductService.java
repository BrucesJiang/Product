package com.product.service;

import com.product.domain.Product;
import com.product.dto.PageBean;
import com.product.exception.ProductException;

public interface ProductService {
    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param category
     * @return
     * @throws ProductException
     */
    public PageBean finProductsPage(int currentPage, int pageSize, String category) throws ProductException;

    /**
     * 根据ID查询商品信息
     * @param id
     * @return
     * @throws ProductException
     */
    public Product findProductInfo(String id) throws ProductException;


}
