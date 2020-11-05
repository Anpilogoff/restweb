package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if(session == null){
            if (req.getRequestURI().endsWith("profile")){
                resp.sendRedirect(req.getServletContext().getContextPath()+ "/registration");
            }else if(req.getRequestURI().endsWith("registration")){

                System.out.println("----");

                req.getRequestDispatcher("registration").forward(req,resp);
            }else{
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
            }


        }else{
            if(req.getSession(false).getAttribute("user") != null){
                req.getSession(false).invalidate();
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
            }
            if(req.getRequestURI().endsWith("ile") && session.getAttribute("nickname")!=null){
                req.getRequestDispatcher("/registerprofile").forward(req,resp);
            }else {
               resp.sendRedirect(req.getServletContext().getContextPath()+"/login");

            }
        }


    }

    @Override
    public void destroy() {

    }
}
