package com.product.servlet;

import com.product.domain.User;
import com.product.exception.UserException;
import com.product.service.UserService;
import com.product.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@WebServlet(name = "UserServlet", urlPatterns = "/user") //相当于在web.xml中配置 <servlet>以及<servlet-mapping>
public class UserServlet extends HttpServlet {

    private final static Logger logger =LoggerFactory.getLogger(UserServlet.class);


    private UserService userService = new UserServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收请求，根据请求方法执行动作
        String method = request.getParameter("method");
        //System.out.println(method);
        if("register".equalsIgnoreCase(method)) {
            register(request,response);
        }
    }
    //用户注册
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取验证码
        String ckcode = request.getParameter("ckcode");
        String ckcode_session = (String)request.getSession().getAttribute("ckcode");
//        System.out.println(ckcode_session);
//        System.out.println(ckcode);
        if(!ckcode_session.equalsIgnoreCase(ckcode)){
            request.getSession().setAttribute("ckcode_msg", "验证码错误!");
            response.sendRedirect("/views/user/register.jsp");
            return;
        }
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString());
            userService.register(user);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }catch(UserException e) {
            request.getSession().setAttribute("user_msg", "用户注册失败，请重新填写");
            response.sendRedirect("/views/user/register.jsp");
            return ;
        }catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }catch (Exception e){
            System.out.println("急啊急啊急");
            e.printStackTrace();
        }
    }
}
