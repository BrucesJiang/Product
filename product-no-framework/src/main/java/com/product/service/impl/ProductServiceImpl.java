package com.product.service.impl;

import com.product.dao.ProductDao;
import com.product.dto.PageBean;
import com.product.domain.Product;
import com.product.exception.ProductException;
import com.product.service.ProductService;
import com.product.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    ProductDao productDao = new ProductDao();

    @Override
    public PageBean finProductsPage(int currentPage, int pageSize, String category) throws ProductException {
        try{
            int count = productDao.count(category); //得到纪录总数
            int totalPage = (int)Math.ceil(count * 1.0 / pageSize); //求出总页数
            List<Product> proucts = productDao.findBooks(currentPage, pageSize, category);

            //封装对象
            PageBean pb = new PageBean();
            pb.setProducts(proucts);
            pb.setCount(count);
            pb.setCurrentPage(currentPage);
            pb.setPageSize(pageSize);
            pb.setTotalPage(totalPage);
            // 在pageBean中添加属性，用于点击上一页或下一页时使用
            pb.setCategory(category);
            return pb;
        }catch(SQLException e){
            logger.error(e.getMessage(), e);
            throw new ProductException("商品查询失败");
        }
    }

    @Override
    public Product findProductInfo(String id) throws ProductException {
        try{
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String sql = "SELECT * FROM products WHERE id = ?";

            return qr.query(sql, new BeanHandler<Product>(Product.class), id);
        }catch(SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ProductException("信息查询失败啦");
        }
    }
}
