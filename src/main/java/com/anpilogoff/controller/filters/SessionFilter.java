package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * That class object will check all incoming requests attribute and forward/redirect them to appropriate servlet
 * or next chain element
 */
@WebFilter
public class SessionFilter implements Filter {
    private FilterConfig config;


    /**
     * Method called by Servlet Container, initialize filter object with values from web descriptor
     *
     * @param filterConfig will be initialized with Servlet Container
     */
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }


    /**
     * @param servletRequest  contains request attributes
     * @param servletResponse designing during request dispatching
     * @param filterChain     contains all web filters described in deployment descriptor
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        System.out.println(request.getRequestURL());
        System.out.println(request.getSession(false));

        if (session != null && session.getAttribute("authStatus").equals("authorized")) {
            System.out.println(request.getSession(false).getAttribute("credentials") + "   from session filter");
            System.out.println(request.getSession(false).getAttribute("authStatus"));
            if (session.getAttribute("authStatus").equals("non authorized")) {
                System.out.println("equls");
                request.getRequestDispatcher("login").forward(request, response);
            }
           filterChain.doFilter(request, response);
        } else {
            System.out.println("home");
            request.getRequestDispatcher("login").forward(request, response);

        }
    }



        public void destroy () {
            config = null;
        }
    }

