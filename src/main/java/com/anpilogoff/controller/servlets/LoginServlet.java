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
 * receive req/resp object from
 *
 * @see com.anpilogoff.controller.filters.SessionFilter
 * In a case when session is null
 * @see LoginServlet#doGet creates a session with attribute name "authStatus" and its value "non authorized"
 * and forwad request to login.html from where with "submit"-button pushing request will be received by
 * @see LoginServlet#doPost (HttpServletRequest, HttpServletResponse) method will be called
 * Otherwise (if session isn't null -
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
     *             * @see HttpServlet#service(ServletRequest, ServletResponse) will be called
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {

            JsonArray array = dao.loginUser(login, password);


            JsonElement object = array.get(0);
            User user = gson.fromJson(object, User.class);
            HttpSession session = req.getSession(true);

           // String nickname = user.getNickname();
            String file_name = dao.getUserAvatar(user.getNickname());
            session.setAttribute("user", array.get(0));
            session.setAttribute("profile", array.get(1));
            session.setAttribute("credentials", array.get(2));

            if (file_name != null) {
                session.setAttribute("avatar", file_name);
                resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
            } else {
                session.setAttribute("avatar", null);
                resp.sendRedirect(req.getServletContext().getContextPath() + "/userhome");
            }
        } catch (SQLException e) {
            log.warn("SQLexcp:  "+ e.getCause() );
        } catch (NullPointerException e) {
            req.getRequestDispatcher("login.html").forward(req, resp);
        }
    }
}




