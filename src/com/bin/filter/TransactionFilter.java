package com.bin.filter;

import com.bin.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.filter
 * @ClassName: TransactionFilter
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/19 16:09
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackAndClose();//回滚事务
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
