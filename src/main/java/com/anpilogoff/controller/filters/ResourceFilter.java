package com.anpilogoff.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ResourceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest.getCharacterEncoding());
        System.out.println("encoding upper");
        System.out.println("res fil");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String fileName = request.getRequestURI();

        File file = null;
        System.out.println(request.getRequestURI());
        if(request.getRequestURI().contains("css")){
            String sb = fileName.substring(44);
            System.out.println(sb+ "     <-------");
            file = new File(request.getServletContext().getRealPath("/resources/css/"+ sb));
            if(file.isFile()) {
                response.setCharacterEncoding("unicode");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET");
                response.setContentLength((int) file.length());
                response.setContentType("text/css");
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getName() + "\"");
                response.setStatus(200);

            }
            }else if(request.getRequestURI().contains("js")) {
            String sub = fileName.substring(43);
            file = new File(request.getServletContext().getRealPath("/resources/js/" + sub));
            if (file.isFile()) {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET");
                response.setHeader("Content-Encoding", "gzip");
                response.setContentType("text/javascript");
                response.setContentLength((int) file.length());
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getName() + "\"");
                response.setStatus(200);
            }

        }
            if(file != null){
                response.getOutputStream().write(Files.readAllBytes(file.toPath()));

            }else{
                response.setStatus(304);
            }
    }


    //todo: Exception during file uploading from "on that moment" userhome_new.jsp page<<<<<<<
    @Override
    public void destroy() {

    }
}
