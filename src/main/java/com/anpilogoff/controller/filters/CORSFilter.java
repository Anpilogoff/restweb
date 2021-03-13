package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        System.out.println(req.getRequestURL() + "      URL from cors");
        System.out.println(req.getQueryString() + "   Query String from url");

        String name = null;
        if(req.getSession(false) == null){
            resp.sendRedirect(req.getServletContext().getContextPath()+"/login");
        }
        System.out.println(req.getRequestURI() + "  " + req.getMethod());
        if (req.getMethod().equals("POST") && req.getRequestURL().toString().endsWith("play")){

            System.out.println("post");
            req.getRequestDispatcher("/play").forward(req, resp);
        } else if (req.getRequestURL().toString().endsWith("play") || req.getMethod().equals("GET")){
            if(req.getQueryString()!=null) {
                name = req.getQueryString().replace("%20", " ");
                name = name.substring(5, name.length());
            }
            System.out.println(1);


            req.getSession(false).setAttribute("songName", name);
            req.getRequestDispatcher("/play").forward(req, resp);
        } else {
            if (req.getRequestURI().contains("resources")) {
                System.out.println(2);
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.setHeader("Access-Control-Allow-Methods", "GET");
                resp.setHeader("Access-Control-Max-Age", "3600");
                resp.setHeader("Access-Control-Allow-Headers", "*");
                req.getRequestDispatcher("/resources").forward(req, resp);
            } else if (req.getRequestURI().contains("img")) {
                System.out.println(3);
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.setHeader("Access-Control-Allow-Methods", "GET");
                resp.setHeader("Access-Control-Max-Age", "3600");
                resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");
                req.getRequestDispatcher("/img").forward(req, resp);
            } else {
                System.out.println(req.getQueryString());
                if (req.getRequestURI().contains("chat")) {
                    resp.setHeader("Upgrade", "websocket");
                    resp.setHeader("Connection", "Upgrade");
                    req.getRequestDispatcher("/chat").forward(req, resp);
                }
                //        }else{
//            System.out.println(4);
//
//            resp.setHeader("Access-Control-Allow-Origin", "*");
//            resp.setHeader("Access-Control-Allow-Methods", "POST, GET");
//            resp.setHeader("Access-Control-Max-Age", "300");
//            resp.setHeader("Access-Control-Allow-Headers", "*");
//
//            System.out.println(req.getRequestURI() + "URI");


            }
        }
    }
}
