package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomePageFilter implements Filter {

    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        System.out.println("filter sterp 3");

        if (request.getRequestURI().contains("login") && request.getMethod().equals("GET") ||
                request.getRequestURI().contains("upload") && request.getMethod().equals("GET") ||
                request.getRequestURI().contains("registration")) {
            System.out.println("aga");
            response.sendRedirect(request.getServletContext().getContextPath() + "/home");
        }
        if (request.getRequestURI().endsWith("home")) {
            if (request.getSession(false).getAttribute("avatar") != null) {
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else request.getRequestDispatcher("userhome.html").forward(request, response);
        }

        if (request.getRequestURI().contains("resources") || request.getRequestURI().contains("dynamic")) {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        this.config = null;

    }
}