package com.product.servlet;

import com.product.domain.Order;
import com.product.domain.Product;
import com.product.domain.User;
import com.product.dto.OrderItem;
import com.product.exception.OrderException;
import com.product.service.OrderService;
import com.product.service.impl.OrderServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Severity;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(OrderServlet.class);



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String method = request.getParameter("method");

            if("createorder".equalsIgnoreCase(method)) { //创建订单
                createOrder(request, response);
            }

    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        //注意添加订单之前，用户一定要登陆
        try {
            BeanUtils.populate(order, request.getParameterMap());
            order.setId(UUID.randomUUID().toString());
            order.setUser((User)request.getSession().getAttribute("user")); //将session对象中的用户信息保存到order中
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            request.getSession().setAttribute("error_msg", "出错啦");
            response.sendRedirect(request.getContextPath() + "/views/error/500.jsp");
        }

        //获取Session对象中的购物车数据
        Map<Product, String> cart = (Map<Product, String>)request.getSession().getAttribute("cart");

        //遍历购物车中的数据，添加到OrderItem对象中，同时把多个orderItem对象放入list集合中
        List<OrderItem> list = new ArrayList<OrderItem>();

        for(Product p : cart.keySet()){
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(p);
            oi.setBuynum(Integer.parseInt(cart.get(p)));

            list.add(oi);
        }

        //将集合放入order中
        order.setOrderItems(list);

        //执行业务逻辑
        OrderService os = new OrderServiceImpl();

        try {
            os.addOrder(order);

            //跳转到支付页
            request.setAttribute("orderid", order.getId());
            request.setAttribute("money", order.getMoney());
            request.getRequestDispatcher("/views/product/pay.jsp").forward(request, response);
        } catch (OrderException e) {
            logger.error(e.getMessage(), e);
            request.getSession().setAttribute("error_msg", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/views/error/500.jsp");
        }

    }
}
