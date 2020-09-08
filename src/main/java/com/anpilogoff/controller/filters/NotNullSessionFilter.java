package com.anpilogoff.controller.filters;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * That class object will check all incoming requests mapping values for compliance with "login","registration" or
 * "userhome" strings values and forward/redirect them to appropriate servlet/.html page or dispatch to next filter.elem
 */
public class NotNullSessionFilter implements Filter {
    private static final Logger log = Logger.getLogger(NotNullSessionFilter.class);
    FilterConfig config;
    /**
     * Method called by Servlet Container, initialize filter object with values from web descriptor
     * @param filterConfig will be initialized with Servlet Container
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    /**
     * Method check session for non-null value and in a case of nullable session will check it's mappings for compliance
     * with "login","registration","userhome" values and in a case of success result will forward/redirect current req
     * to appropriate servlet or dispatch it to a next filter chain element processing
     * @param servletRequest  contains request attributes
     * @param servletResponse designing during request dispatching
     * @param filterChain     contains all web filters described in deployment descriptor */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session != null) {
            try {
                if (request.getRequestURI().endsWith("login")) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
                } else if (request.getRequestURI().endsWith("registration")) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
                } else if (request.getRequestURI().endsWith(request.getServletContext().getContextPath()+"/userhome")) {
                    request.getRequestDispatcher("userhome.html").forward(request, response);
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    /**
     * Method is call by servlet container to destroy Servlet object
     */
    public void destroy() {
        this.config = null;
    }
}
