package com.product.servlet;

import com.product.dto.PageBean;
import com.product.exception.ProductException;
import com.product.service.ProductService;
import com.product.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageSearchServlet", urlPatterns = "/pageServlet", initParams = {@WebInitParam(name="pageSize", value = "4"), @WebInitParam(name="initPage", value = "1")})
public class PageSearchServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(PageSearchServlet.class);

    private static int pageSize;//初始化每页显示的记录数
    private static int initPage;//初始页

    private ProductService ps = new ProductServiceImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        pageSize = Integer.parseInt(config.getInitParameter("pageSize"));
        initPage = Integer.parseInt(config.getInitParameter("initPage"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String category = request.getParameter("category");
        //导航按钮的查询条件
        if(category==null){
            category="";
        }

        int currentPage = initPage;//当前页
        String currPage = request.getParameter("currentPage");//从上一页或下一页得到的数据
        if(currPage!=null&&!"".equals(currPage)){//第一次访问资源时，currPage可能是null
            currentPage = Integer.parseInt(currPage);
        }
        //分页查询，并返回PageBean对象
        PageBean pb = null;
        try {
            pb = ps.finProductsPage(currentPage,pageSize,category);
        } catch (ProductException e) {
           logger.error(e.getMessage(), e);
           request.getSession().setAttribute("error_msg", "商品查询出错啦!");
           response.sendRedirect("/views/error/500.jsp");
        }

        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/views/product/product_list.jsp").forward(request, response);
    }
}
