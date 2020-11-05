package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession sess = req.getSession(false);


        System.out.println(sess + " + session");
        if (sess != null) {
            if (req.getRequestURI().endsWith("ile")) {
                System.out.println("neusto");
                req.getRequestDispatcher("/registerprofile").forward(req, resp);
            } else if (sess.getAttribute("avatar") != null) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/home");

            } else if (sess.getAttribute("avatar") == null) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/userhome");
            }
//            } else if (sess.getAttribute("user") != null) {
//                if (sess.getAttribute("avatar") != null) {
//                    resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
//                } else {
//                    resp.sendRedirect(req.getServletContext().getContextPath() + "/userhome");
//                }
        } else {
            System.out.println("else login filter");
            if (req.getRequestURI().endsWith("tion")) {
                req.getRequestDispatcher("/registration").forward(req, resp);
            } else {
                System.out.println("login filler");
                req.getRequestDispatcher("/login").forward(req, resp);

            }
        }
    }
}


