package com.bin.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.filter
 * @ClassName: ManagerFilter
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/19 15:34
 */
public class ManagerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {

    }
}
