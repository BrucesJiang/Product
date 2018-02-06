package com.product.filter;

import com.product.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个过滤器处理以内容：
 * 用户在进入结算中心之前必须登陆。
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String uri = request.getRequestURI();
        if(uri.endsWith("order.jsp")) {
            User user = (User)request.getSession().getAttribute("user");
            if(user == null) {
                response.sendRedirect(request.getContextPath() + "/views/user/login.jsp");
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
