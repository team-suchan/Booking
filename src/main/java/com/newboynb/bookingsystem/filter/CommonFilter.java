package com.newboynb.bookingsystem.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CommonFilter", urlPatterns = "/*")
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", ((HttpServletRequest)servletRequest).getHeader("Origin"));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
