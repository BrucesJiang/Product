package com.product.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class EncodingFilter implements javax.servlet.Filter {
    private String encoding;

    public void destroy() {

    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        //过滤request请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletRequest commonRequest = new FilterRequest(httpServletRequest);

        //过滤response请求
        resp.setContentType("text/html; charset=utf-8");

        //放行
        chain.doFilter(commonRequest, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        this.encoding = config.getInitParameter("encoding");
    }


    //自定义request过滤类，使用装饰模式增强， 这里仅仅增强了几种方法。
    private static class FilterRequest extends HttpServletRequestWrapper {
        Logger logger = LoggerFactory.getLogger(FilterRequest.class);

        private HttpServletRequest request;
        private boolean hasEncoding = false;

        public FilterRequest(HttpServletRequest request) {
            super(request); //必须做
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            Map<String, String[]> parameterMap = getParameterMap();
            String[] values = parameterMap.get(name);
            if (values == null) {
                return null;
            }
            return values[0]; // 取回参数的第一个值
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            //获取request请求方式 get或post 等，这里仅仅处理了get和post请求
            String method = request.getMethod();

            if ("post".equalsIgnoreCase(method)) { //如果是post请求
                try {
                    //重新编码
                    request.setCharacterEncoding("UTF-8");
                    return request.getParameterMap();
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage(), e);
                }
            } else if ("get".equalsIgnoreCase(method)) { //处理get请求
                Map<String, String[]> parameterMap = request.getParameterMap();
                if (!hasEncoding) { //如果没有没有编码, 仅仅进行一次编码
                    for (String pm : parameterMap.keySet()) {
                        String[] values = parameterMap.get(pm);
                        if (values != null) {
                            for (int i = 0; i < values.length; i++) {
                                try {
                                    //request默认传输格式为 ISO-8859-1
                                    values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    //logger.error(e.getMessage(), e);
                                }
                            }
                        }
                    }
                    hasEncoding = true;
                }
                return parameterMap;
            }
            return super.getParameterMap();
        }

        @Override
        public String[] getParameterValues(String name) {
            Map<String, String[]> parameterMap = getParameterMap();
            String[] values = parameterMap.get(name);
            return values;
        }
    }
}
