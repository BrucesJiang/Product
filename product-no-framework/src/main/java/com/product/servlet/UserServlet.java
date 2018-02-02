package com.product.servlet;

import com.product.domain.User;
import com.product.exception.UserException;
import com.product.service.UserService;
import com.product.service.impl.UserServiceImpl;
import com.product.utils.MD5Utils;
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
        if("register".equalsIgnoreCase(method)) { //注册
            register(request,response);
        }else if("active".equalsIgnoreCase(method)) { //激活用户
            activeUser(request, response);
        }else if("login".equalsIgnoreCase(method)) { //登陆
            login(request, response);
        }else if("logout".equalsIgnoreCase(method)){ //登出
            logout(request, response);
        }
    }

    //用户登出
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().invalidate();//使session销毁
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }


    //用户登录
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取输入参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{
            //1. 检查用户是否存在
            User user = userService.findUser(username, MD5Utils.md5(password));
            //如果不存在
            if (user == null) {
                request.getSession().setAttribute("user_msg","用户名或密码错误，或者您没有激活账号？");
                response.sendRedirect("/views/user/login.jsp");
            }else {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/views/home.jsp").forward(request, response);
            }
        }catch(UserException e){
            logger.error(e.getMessage(), e);
            request.getSession().setAttribute("user_msg", e.getMessage());
            response.sendRedirect("/views/user/login.jsp");
        }
    }

    //激活用户
    private void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String activeCode = request.getParameter("activeCode");
        try {
            userService.activeUser(activeCode);
            request.getSession().setAttribute("active_msg","帐户激活成功！");
            response.sendRedirect("views/user/activesuccess.jsp");
        } catch (UserException e) {
            logger.error(e.getMessage(), e);
            request.getSession().setAttribute("active_msg","激活失败，请重新激活！");
            response.sendRedirect("views/user/activesuccess.jsp");
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
            user.setPassword(MD5Utils.md5(user.getPassword())); //对密码加密
            userService.register(user);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/views/user/registersuccess.jsp").forward(request, response);
        }catch(UserException e) {
            request.getSession().setAttribute("user_msg", "用户注册失败，请重新填写");
            response.sendRedirect("/views/user/register.jsp");
            return ;
        }catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
