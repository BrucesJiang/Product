package com.product.servlet;

import com.product.domain.Product;
import com.product.exception.ProductException;
import com.product.service.ProductService;
import com.product.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private ProductService ps = new ProductServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if ("findbookinfo".equalsIgnoreCase(method)) { // 查询图书详细信息
            findBookInfo(request, response);
        }

    }


    private void findBookInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");

        try {
            Product product = ps.findProductInfo(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/views/product/product_info.jsp").forward(request, response);
        } catch (ProductException e) {
            logger.error(e.getMessage(), e);
            request.getSession().setAttribute("error_msg", e.getMessage());
            response.sendRedirect("/views/error/500.jsp");
        }
    }
}
