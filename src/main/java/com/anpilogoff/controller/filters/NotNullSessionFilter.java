package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NotNullSessionFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session != null) {
            try {
                if (request.getRequestURI().endsWith("login")) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
                } else if (request.getRequestURI().endsWith("registration")) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
                } else if (request.getRequestURI().endsWith("userhome")) {
                    request.getRequestDispatcher("userhome.html").forward(request, response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy() {

    }
}
