package com.product.servlet;

import com.product.domain.Product;
import com.product.domain.User;
import com.product.exception.ProductException;
import com.product.service.ProductService;
import com.product.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 添加购物车
 */
@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(CartServlet.class);

    private ProductService productService = new ProductServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取方法名称
        String method = request.getParameter("method");

        if ("addcart".equalsIgnoreCase(method)) {//添加到购物车
            addCart(request, response);
        }else if("chn".equalsIgnoreCase(method)) { //改变购物车商品数量
            changeNum(request, response);
        }
    }

    //改变上平数量
     private void changeNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String id = request.getParameter("id");
         String num = request.getParameter("num");
         //注意：只能重写id的hashCode
         Product product = new Product();
         product.setId(id);

         HttpSession session = request.getSession();

         Map<Product, String> cart = (Map<Product, String>)session.getAttribute("cart");

         //如果商品数量为0,从购物车中删除
         if("0".equals(num)){
            cart.remove(product);
         }

         //如果含有相同的Id的商品
         if(cart.containsKey(product)) {
             cart.put(product, num);
         }

         response.sendRedirect(request.getContextPath() + "/views/product/cart.jsp");
     }

    //添加到购物车
    private void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 1. 获取用户信息
//        User user = (User)request.getSession().getAttribute("user");
//
//        /**
//         * 2. 分为两种情况：
//         *  (1) 用户已经登陆，则将用户购物车信息持久到数据库
//         *  (2) 用户没有登陆，则将用户购物车信息持久化到用户客户端Cookie
//         *
//         *  会造成几种情况：
//         *  用户未登录，添加购物车,
//         */
//        if(user != null) {//如果用户已经登陆
//
//        }else { //用户没有登陆
//
//        }
//        //获取获取商品ID
//        String pId = request.getParameter("id");
//        //1. 获取Cookie中的购物车

        String id = request.getParameter("id");

        try {
            Product product = productService.findProductInfo(id);

            //从Session中取出购物车
            HttpSession session = request.getSession();
            //key为Product对象的Hash序列， Value为其数量
            Map<Product, String> cart = (Map<Product, String>)session.getAttribute("cart");

            int num = 1;
            //如果是第一个访问购物车，则创建购物车
            if(cart == null) {
                cart = new HashMap<Product, String>();
            }

            //查看当前集合中是否存在product商品，如果存在就将对象取出并加1
            if(cart.containsKey(product)) {
                num = Integer.parseInt(cart.get(product) + 1);
            }

            //将图书重新放入购物车
            cart.put(product, String.valueOf(num));

            //将购物车重新放回Session域
            session.setAttribute("cart", cart);

            request.getRequestDispatcher("/views/product/cart.jsp").forward(request, response);
        } catch (ProductException e) {
            logger.error(e.getMessage(), e);
            request.getSession().setAttribute("error_msg", "出错啦");
            response.sendRedirect("/views/error/500.jsp");
        }
    }
}
