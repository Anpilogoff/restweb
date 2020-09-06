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
                if (session == null) {
                    if (request.getRequestURI().endsWith("login") && request.getMethod().equals("POST")) {
                        request.getRequestDispatcher("/login").forward(request, response);
                    }else if (request.getRequestURI().endsWith("login") && request.getMethod().equals("GET")) {
                        request.getRequestDispatcher("login.html").forward(request, response);
                    } else if (request.getRequestURI().endsWith("registration")) {
                        request.getRequestDispatcher("registration.html").forward(request, response);
                    }else if(request.getRequestURI().endsWith("userhome")){
                        response.sendRedirect(request.getServletContext().getContextPath()+ "/login");
                    }
                } else {
                    filterChain.doFilter(request,response);
                }
            } catch (ServletException e) {
                System.out.println();
            }catch (IOException e){
                System.out.println();
            }
        }



        public void destroy () {
            config = null;
        }
    }

