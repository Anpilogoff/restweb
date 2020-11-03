package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession sess = req.getSession(false);
        String uri = req.getRequestURI();

        if(sess==null){
            resp.sendRedirect(req.getServletContext().getContextPath()+"/login");
        }else{
            if(sess.getAttribute("user")!=null && sess.getAttribute("profile")!=null){
               if(uri.endsWith("home") && sess.getAttribute("avatar")!=null){
                   req.getRequestDispatcher("/home.jsp").forward(req,resp);
               }else if(uri.endsWith("userhome")&& sess.getAttribute("avatar")==null){
                   req.getRequestDispatcher("/userhome.jsp").forward(req,resp);  //html
               }else if(uri.endsWith("userhome") && sess.getAttribute("avatar")!= null){
                      resp.sendRedirect(req.getServletContext().getContextPath()+"/home");
               }else if(uri.equals("/restweb/home")&& sess.getAttribute("avatar")==null){
                      resp.sendRedirect(req.getServletContext().getContextPath()+"/userhome");
              }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
