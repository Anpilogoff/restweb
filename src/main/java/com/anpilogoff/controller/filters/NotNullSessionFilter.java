package com.anpilogoff.controller.filters;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * That class object will check all incoming requests mapping values for compliance with "login","registration" or
 * "userhome" strings values and forward/redirect them to appropriate servlet/.html page or dispatch to next filter.elem
 * The logic described in the methods of this class will be executed only if the  request "session" attribute doesn't
 * correspond to "null"-value
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
     * That method will run and in a case of nullable session will check it's mappings for compliance
     * with "login","registration","userhome" values and in a case of success result will forward/redirect current req
     * to appropriate servlet or dispatch it to a next filter chain element processing
     * @param servletRequest  contains request attributes
     * @param servletResponse designing during request dispatching
     * @param filterChain     contains all web filters described in deployment descriptor */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session != null) {
            if(req.getRequestURI().contains("uploadservlet") && req.getMethod().equals("POST") ){
                System.out.println("forwarded to upload servlet");
                req.getRequestDispatcher("/uploadservlet").forward(req,resp);
            }

            if (req.getRequestURI().contains("sounds/system/") || req.getRequestURI().contains("resources/")) {
                filterChain.doFilter(req, resp); }

            if (req.getSession(false).getAttribute("userNickname") != null &&
                    req.getRequestURI().endsWith("registerprofile") && req.getMethod().equals("POST"))
            {
                System.out.println(req.getSession(false).getAttribute("userNickname") + " from not null session filter");
                req.getRequestDispatcher("registerprofile").forward(req, resp);
            }
                filterChain.doFilter(req,resp);
        }
        else log.warn("Invalid \"session\" value in Filter chain SESSION IS NULL: "+ req.getRequestURI());}




    /**
     * Method is call by servlet container to destroy Servlet object
     */
    public void destroy() {
        this.config = null;
    }
}
