package com.anpilogoff.controller.filters;

import com.sun.deploy.net.HttpRequest;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/** шs the next item in the filter chain users with zero session state, redirects to the login page,
 * and prevents users with an open session from entering it */
public class LoginPageFilter implements Filter {

    /**   Current class logger  */
    private org.apache.log4j.Logger log = Logger.getLogger(LoginPageFilter.class);

    /** Empty
     * @see FilterConfig class variable filled with Servlet Container during filter initialization */
    private FilterConfig config;

    /** Method handle properties from deployment descriptor(web.xml)
     * @param filterConfig parameter is set by servlet container during filter initialization */
    public void init(FilterConfig filterConfig) {
        config = filterConfig;
    }

    /**
     * used to check the session value for zero and compare the authSession attribute with the value "authorized";
     * if at least one condition is not met, it redirects the request to the login page;
     * but if the session is open - and the attribute matches the value "authorized" -
     * the filter will check the end of the request address for matching the value "login" and if the result is true -
     * redirect to the user page (restricting access to the authorization page)
     *
     * @param servletRequest object created by servlet container and initialized with incoming request values
     * @param servletResponse object created by servlet container in a moment of request reseiving
     * @param filterChain  contains all web filters described in deployment descriptor
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("authStatus").equals("authorized")) {
            if (request.getRequestURI().endsWith("login")) {
                response.sendRedirect(request.getServletContext().getContextPath() + "/userhome");
                request.getRequestDispatcher("userhome").forward(request,response);
            }
            request.getRequestDispatcher("/userhome").forward(request, response);

        } else if (session != null && !session.getAttribute("authStatus").equals("authorized")) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/login");

        } else {
            response.sendRedirect(request.getServletContext().getContextPath() + "/login");
        }
    }


    /** make filter config object as NULL */
    public void destroy() {
        this.config = null;
    }
}
