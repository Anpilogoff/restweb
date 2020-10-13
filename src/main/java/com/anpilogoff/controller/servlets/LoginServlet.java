package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * That class in case of nullable session will get "login"/"password" string values from "login.html"-page, and after that
 * will create variable of type
 * @see JsonArray which will be initialized with returnable JsonArray class object through executing  method
 * @see UserDAO#loginUser(String, String), and after it's initializing - objct of class
 * @see User will be declare and initialize by parcing of 1-st element of returned JsonArray class array and use to get
 * current trying to login user's nickname(to get avatar-file name from data base) and other array element to set them
 * as session attributes..
 * In case of NPE(array not initialized) - user will forward to same "login.html" page, in success case - avatar file
 * name will be checked on null and depending on this fact user will be redirect on "/home" or "/userhome" uri
 **/
@WebServlet
public class LoginServlet extends HttpServlet {
    private Logger log;
    private UserDAO dao;
    private Gson gson;


    @Override
    public void init() {
        this.dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
        gson = new Gson();
        log = Logger.getLogger(LoginServlet.class);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }


    /**
     * @param req  is object created by Tomcat after request receiving and initialized with request attributes
     * @param resp is empty-object created by Tomcat after request receiving(will be initialized when method
     * @see HttpServlet#service(HttpServletRequest, HttpServletResponse) will be called
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println("login:  " + login.length());
        System.out.println("password:  "+ password);

        try {
            System.out.println(login + "         login");
            System.out.println(password + "     password");
            if(login.isEmpty()|| password.length()==0){
                 req.getRequestDispatcher("login.html").forward(req,resp);
            }else{
                JsonArray array = dao.loginUser(login, password);

                if(array == null){
                    resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
                }else if (array.get(0) != null) {
                    JsonElement object = array.get(0);
                    User user = gson.fromJson(object, User.class);

                    System.out.println("User"+ user);

                    System.out.println("object userJson from LOGINSERVLET post method:       " + object);
                    System.out.println("user parsed from userJson object " + user.getNickname());


                    HttpSession session = req.getSession(true);
                    String file_name = dao.getUserAvatar(user.getNickname());

                    session.setAttribute("user", array.get(0));
                    session.setAttribute("profile", array.get(1));
                    session.setAttribute("credentials"  , array.get(2));

                    System.out.println(file_name    + "   from login servlet");

                    if (file_name != null) {
                        session.setAttribute("avatar", file_name);
                        resp.setContentType("img/*");
                        resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
                    } else {
                        session.setAttribute("avatar", null);
                        resp.setContentType("img/*");

                        resp.sendRedirect(req.getServletContext().getContextPath() + "/userhome");


                    }
                }

            }
        } catch (SQLException | IOException | ServletException e) {
            log.warn("SQLexception:  " + e.getCause() );
            req.getRequestDispatcher("login.html").forward(req,resp);

        }
            //проверка наличия файла в папке temp
            //else - redirect на userhome


    }
}