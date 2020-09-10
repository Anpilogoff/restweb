package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * That class object will check all incoming requests sessions for null value and their mapping values for compliance
 * with "login","registration" or "userhome" strings values and forward/redirect them to appropriate servlet/.html page
 * or dispatch to next filter chain element.
 */
@WebFilter
public class SessionFilter implements Filter {
    private FilterConfig config;

    /**
     * Method called by Servlet Container, initialize filter object with values from web descriptor
     * @param filterConfig will be initialized with Servlet Container
     */
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }


    /**
     * Method check session for null value and in a case of session is null - will check their mappings for compliance
     * with "login","registration","userhome" values and in a case of success result will forward/redirect current req
     * to a next filter chain element or dispatch it to a next filter chain element processing
     * @param servletRequest  contains request attributes
     * @param servletResponse designing during request dispatching
     * @param filterChain     contains all web filters described in deployment descriptor */
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession(false);

            System.out.println(request.getRequestURI());
            System.out.println(request.getMethod());

            try {
                if (session == null) {
                    if (request.getRequestURI().endsWith("login") && request.getMethod().equals("POST")) {
                        request.getRequestDispatcher("login").forward(request, response);
                    }
                    else if (request.getRequestURI().endsWith("login") && request.getMethod().equals("GET")) {
                        request.getRequestDispatcher("login.html").forward(request, response);
                    }
                    else if (request.getRequestURI().endsWith("registration") && request.getMethod().equals("POST")) {
                        request.getRequestDispatcher("registration").forward(request, response);
                    }
                    else if(request.getRequestURI().endsWith("registration") && request.getMethod().equals("GET") ||
                            request.getRequestURI().endsWith("registration.html") && request.getMethod().equals("GET")){
                        request.getRequestDispatcher("registration.html").forward(request,response);
                    }
                    else if(request.getRequestURI().endsWith("registerprofile") && request.getMethod().equals("GET")){
                        System.out.println("forward from session filter to  html registerprofile");

                        request.getRequestDispatcher("registerprofile.html").forward(request,response);
                    }
                    else if(request.getRequestURI().endsWith("registerprofile") && request.getMethod().equals("POST")){
                        System.out.println("forward to reg profile servlet  by session filter");
                        request.getRequestDispatcher("registerprofile").forward(request,response);
                    }


                    else if(request.getRequestURI().endsWith("registerprofile") && request.getMethod().equals("POST")){
                        request.getRequestDispatcher("registerprofile").forward(request,response);
                    }
                    else if(request.getRequestURI().endsWith("userhome")){
                        response.sendRedirect(request.getServletContext().getContextPath()+ "/login");
                    }
                } else {
                    System.out.println("dof");
                    filterChain.doFilter(request,response);
                }
            } catch (ServletException e) {
                System.out.println();
            }catch (IOException e){
                System.out.println();
            }
        }


    /**
     * Method is call by servlet container to destroy Servlet object
     */
    public void destroy () {
            config = null;
        }
    }

