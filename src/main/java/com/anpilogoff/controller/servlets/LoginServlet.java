package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.ConnectionBuilder;
import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.Dao;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.UserCredentials;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * receive req/resp object from
 * @see com.anpilogoff.controller.filters.SessionFilter
 * In a case when session is null
 * @see LoginServlet#doGet creates a session with attribute name "authStatus" and its value "non authorized"
 * and forwad request to login.html from where with "submit"-button pushing request will be received by
 * @see LoginServlet#doPost (HttpServletRequest, HttpServletResponse) method will be called
 * Otherwise (if session isn't null -
 */
@WebServlet
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private UserDAO dao;

    @Override
    public void init() throws ServletException {
        this.dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req,resp);
    }

    /**
     * @param req  is object created by Tomcat after request receiving and initialized with request attributes
     * @param resp is empty-object created by Tomcat after request receiving(will be initialized when method
     * @see HttpServlet#service(ServletRequest, ServletResponse) will be called
     */
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        JsonArray array = dao.loginUser(login, password);
        if(array != null){
            HttpSession session = req.getSession(true);

            JsonObject object = (JsonObject) array.get(0);
            System.out.println("JSON OBJECT:  "+ array.get(0));
            String nicknameWithBracers = String.valueOf(object.get("nickname"));
            String nickname = nicknameWithBracers.replace("\"","");
            String file_name = dao.getUserAvatar(nickname);

            session.setAttribute("user", array.get(0));
            session.setAttribute("profile", array.get(1));
            session.setAttribute("credentials", array.get(2));

            if(file_name != null){
                session.setAttribute("avatar", file_name);
            //    resp.sendRedirect(req.getServletContext().getContextPath() + "userhomePhoto.jsp");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
                System.out.println("from login servlet +");
            }else {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/userhome");
                System.out.println("from login servlet -");

            }
        }else{
            resp.sendRedirect(req.getServletContext().getContextPath()+ "/login");
        }
    }
}



