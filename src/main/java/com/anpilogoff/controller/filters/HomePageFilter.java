package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if(request.getSession(false) != null){
            if(request.getRequestURI().contains("login") ||
                    request.getRequestURI().contains("registration")||
                    request.getRequestURI().endsWith("registration.html") ||
                    request.getRequestURI().contains("le.html") && request.getMethod().equals("GET") ||
                    request.getRequestURI().endsWith("ile") ||
                    request.getRequestURI().contains("upload") && request.getMethod().equals("GET"))
            {
                response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
            }

            if(request.getRequestURI().endsWith("userhome") || request.getRequestURI().endsWith("/userhome.html")){
                request.getRequestDispatcher("userhome.html").forward(request,response);
            }else if(request.getRequestURI().endsWith("home") || request.getRequestURI().endsWith("/home.jsp")){
                request.getRequestDispatcher("home.jsp").forward(request,response);
            }
        }
    }

    @Override
    public void destroy() {
        this.config = null;

    }
}
