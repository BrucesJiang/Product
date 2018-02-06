package com.product.dto;

import com.product.domain.Product;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class PageBean {
    private int currentPage;
    private int pageSize;
    private int count;
    private int totalPage;
    private List<Product> products;
    private String category;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("currentPage", currentPage)
                .append("pageSize", pageSize)
                .append("count", count)
                .append("totalPage", totalPage)
                .append("products", products)
                .append("category", category)
                .toString();
    }
}
