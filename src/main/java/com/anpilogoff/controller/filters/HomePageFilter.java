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

        if (session != null) {
            if (request.getSession(false).getAttribute("avatar") != null && request.getRequestURI().endsWith("restweb/home")) {
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else if (request.getSession(false).getAttribute("avatar") == null && request.getRequestURI().endsWith("userhome")) {
                request.getRequestDispatcher("userhome.html").forward(request, response);
            } else if (request.getSession(false).getAttribute("avatar") == null && request.getRequestURI().endsWith("restweb/home")) {
                response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
            } else if (request.getSession(false).getAttribute("avatar") != null && request.getRequestURI().endsWith("restweb/userhome")) {
                response.sendRedirect(request.getServletContext().getContextPath() + "/home");
            }
            if (request.getRequestURI().contains("resources") || request.getRequestURI().contains("dynamic")) {
                filterChain.doFilter(request, response);
            } else if (request.getMethod().equals("GET")) {
                if (request.getRequestURI().contains("login") || request.getRequestURI().contains("upload") || request.getRequestURI().contains("registration") ||
                        request.getRequestURI().contains("registerprofile") || request.getRequestURI().contains("uploadservlet")) {
                    if (request.getSession(false).getAttribute("avatar") != null) {
                        response.sendRedirect(request.getServletContext().getContextPath() + "/home");
                    } else if (request.getSession(false).getAttribute("avatar") == null) {
                        response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
                    }
                }
            }
        } else {
            if (request.getRequestURI().contains("resources") || request.getRequestURI().contains("dynamic")) {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        this.config = null;

    }
}