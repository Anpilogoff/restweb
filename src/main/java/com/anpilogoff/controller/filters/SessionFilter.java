package com.anpilogoff.controller.filters;

import org.apache.log4j.Logger;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;


/**
 * Each of the incoming requests will go through the session filter and if requests \"session\" attribute value  is
 *  be zero - SessionFilter will check the request on:
 * 1) containing one of below words in URI:
 * -/home,
 * -/upload,
 * -'registerprofile '
 * <p>
 * 2) checks the request type
 * <p>
 * 3) Depending on previous actions  result of , redirects to a specific servlet required method;
 * <p>
 * But if "session" isn't null -> request URi will check on equals to "registerprofile" and it's session "nick" attribute
 * and in success case - request processing will forward to
 * @see com.anpilogoff.controller.servlets.ProfileRegistrationServlet ("/registerprofile"), else -
 * @see FilterChain#doFilter(ServletRequest, ServletResponse) will be called and request's processing will be perform
 * by the next filter chain element.
 *
 * In case when session is "null"
 *
 */
@WebFilter
public class SessionFilter implements Filter {

    private static final Logger log = Logger.getLogger(SessionFilter.class);
    private FilterConfig config;

    /*** Method called by Servlet Container, initialize filter object with values from deployment descriptor
     * @param filterConfig will be initialized with Servlet Container*/
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }


    /*** Method check session for null value and in a case of session is null - will check their mappings for compliance
     * with "login","registration","userhome" values and in a case of success result will forward/redirect current req
     * to appropriate servlet(html/jsp). In "not null session case - request processing will pass to the next FCE(elemt)
     * @param servletRequest  contains request attributes
     * @param servletResponse designing during request dispatching
     * @param filterChain     contains all web filters described in deployment descriptor*/
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("URI:  " + uri);
        System.out.println("Session:  " + session);

        try {
            if (session == null) {
                if (uri.contains("home") || uri.contains("upload") || uri.contains("register")) {
                    response.sendRedirect("login");
                }
                if (request.getMethod().equals("GET")) {
                    if (uri.contains("resources")) {
                        filterChain.doFilter(request, response);
                    } else if (uri.endsWith("login")) {
                        request.getRequestDispatcher("login.html").forward(request, response);
                    } else if (uri.endsWith("registration")) {
                        request.getRequestDispatcher("registration.html").forward(request, response);
                    }
                } else if (request.getMethod().equals("POST")) {
                    if (uri.endsWith("login")) {
                        request.getRequestDispatcher("login").forward(request, response);
                    } else if (uri.endsWith("registration")) {
                        request.getRequestDispatcher("registration").forward(request, response);
                    }
                }
            }else {
                if (request.getRequestURI().endsWith("registerprofile") && request.getSession(false).getAttribute("userNickname") != null) {
                    request.getRequestDispatcher("registerprofile").forward(request, response);
                }else if (request.getRequestURI().endsWith("logout")){
                    request.getRequestDispatcher("/logout").forward(request,response);
                }else if(request.getRequestURI().contains("index.html")){
                    request.getRequestDispatcher("index.html").forward(request,response);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        } catch (IOException | ServletException e) {
            //todo
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


