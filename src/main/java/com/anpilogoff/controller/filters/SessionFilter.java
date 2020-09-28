package com.anpilogoff.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Each of the incoming requests will go through the session filter and if requests \"session\" attribute value  of the will be zero - SessionFilter will check the request, and more specifically:
 * 1) checking request  mapping-string ends with:
 * -'login',
 * -'registration',
 * -'registerprofile '
 * <p>
 * 2) checks the request type
 * <p>
 * 3) Depending on previous actions  result of , redirects to a specific servlet required method;
 * <p>
 * But if "session" isn't null ->  request's dispathcing will be executed by next filter chain element.
 */
@WebFilter
public class SessionFilter implements Filter {

    private static final Logger log = Logger.getLogger(SessionFilter.class);
    private FilterConfig config;

    /**
     * Method called by Servlet Container, initialize filter object with values from deployment descriptor
     *
     * @param filterConfig will be initialized with Servlet Container
     */
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }


    /**
     * Method check session for null value and in a case of session is null - will check their mappings for compliance
     * with "login","registration","userhome" values and in a case of success result will forward/redirect current req
     * to a next filter chain element or dispatch it to a next filter chain element processing
     *
     * @param servletRequest  contains request attributes
     * @param servletResponse designing during request dispatching
     * @param filterChain     contains all web filters described in deployment descriptor
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        try {
            if (session == null) {
                if(request.getRequestURI().endsWith("home") || request.getRequestURI().endsWith("userhome") ||
                request.getRequestURI().endsWith("uploadservlet")){
                    response.sendRedirect(request.getServletContext().getContextPath() + "/login");
                }
                if (request.getRequestURI().endsWith("login") && request.getMethod().equals("POST")) {
                    request.getRequestDispatcher("login").forward(request, response);
                } else if (request.getRequestURI().endsWith("login") && request.getMethod().equals("GET")) {
                    request.getRequestDispatcher("login.html").forward(request, response);
                } else if (request.getRequestURI().endsWith("registration") && request.getMethod().equals("POST")) {
                    request.getRequestDispatcher("registration").forward(request, response);
                } else if (request.getRequestURI().endsWith("registration") && request.getMethod().equals("GET") ||
                        request.getRequestURI().endsWith("registration.html") && request.getMethod().equals("GET")) {
                    request.getRequestDispatcher("registration.html").forward(request, response);
                } else if (request.getRequestURI().endsWith("registerprofile") && request.getMethod().equals("GET")) {
                    request.getRequestDispatcher("registerprofile.html").forward(request, response);
                } else if (request.getRequestURI().endsWith("registerprofile") && request.getMethod().equals("POST")) {
                    request.getRequestDispatcher("registerprofile").forward(request, response);
                } else if (request.getRequestURI().endsWith("registerprofile") && request.getMethod().equals("POST")) {
                    request.getRequestDispatcher("registerprofile").forward(request, response);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (ServletException | IOException e) {
            log.warn("exception" + e.getCause());
            e.printStackTrace();
        }
    }


    /**
     * Method is call by servlet container to destroy Servlet object
     */
    public void destroy() {
        config = null;
    }
}

