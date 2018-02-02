package com.product.filter;

import com.product.domain.User;
import com.product.exception.UserException;
import com.product.service.UserService;
import com.product.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = "/user")
public class AutoLoginFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(AutoLoginFilter.class);

    private FilterConfig filterConfig;

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.转换request和response对象
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String uri = request.getRequestURI(); // login.jsp

        String path = request.getContextPath(); // autologin
        path = uri.substring(path.length());

        //放行其他页面
        if(!("/login.jsp".endsWith(path) || "/user".endsWith(path))){
            User user = (User)request.getSession().getAttribute("user");
            //如果用户没有登陆过，执行自动登录
            if (user == null) {//避免无用操作
                //2.处理业务
                //得到Cookie数组
                Cookie[] cookies = request.getCookies();

                Cookie cookie = null;
                String username = null;
                String password = null;
                //查找目标
                for(int i = 0; cookies != null && i < cookies.length; i ++) {
                    if("user".equals(cookies[i].getName())){
                        cookie = cookies[i];
                        String value = cookie.getValue();
                        String[] values = value.split("&");
                        username = values[0];
                        password = values[1];
                    }
                }
                UserService us = new UserServiceImpl();
                User u = null;
                try {
                    u = us.findUser(username, password);
                } catch (UserException e) {
                    logger.error(e.getMessage(), e);
                }
                if(u != null) {
                    request.getSession().setAttribute("user", u);
                }
            }

        }
        //3.放行
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
}
