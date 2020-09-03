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
     * @param filterChain     contains all web filters described in deployment descriptor */
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        try {
            if (session != null && session.getAttribute("authStatus").equals("authorized")) {
                if (session.getAttribute("authStatus").equals("non authorized")) {
                    request.getRequestDispatcher("login").forward(request, response);
                }
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("login").forward(request, response);

            }
        }catch (ServletException e){
            System.out.println("sx");
        }catch (IOException e){
            System.out.println("s");
        }
    }


        public void destroy () {
            config = null;
        }
    }

