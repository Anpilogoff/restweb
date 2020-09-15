package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.ConnectionBuilder;
import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.Dao;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.UserCredentials;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * receive req/resp object from
 * @see com.anpilogoff.controller.filters.SessionFilter
 *
 *  In a case when session is null
 *  @see LoginServlet#doGet creates a session with attribute name "authStatus" and its value "non authorized"
 * and forwad request to login.html from where with "submit"-button pushing request will be received by
 * @see LoginServlet#doPost (HttpServletRequest, HttpServletResponse) method will be called
 * Otherwise (if session isn't null -
 */
@WebServlet
public class LoginServlet extends HttpServlet {
  private static final  Logger log = Logger.getLogger(LoginServlet.class);
  private UserDAO dao;
//    /**
//     * @param request  is object created by Tomcat after request receiving and initialized with request attributes
//     * @param response is empty-object created by Tomcat after request receiving(will be initialized when method
//     * @see HttpServlet#service(ServletRequest, ServletResponse) will be called */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("forard");
//        request.getRequestDispatcher("login.html").forward(request,response);
//    }

    @Override
    public void init() throws ServletException {
        this.dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
    }

    /**
     * @param req  is object created by Tomcat after request receiving and initialized with request attributes
     * @param resp is empty-object created by Tomcat after request receiving(will be initialized when method
     * @see HttpServlet#service(ServletRequest, ServletResponse) will be called */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


                if (!req.getParameter("login").equals("") && !req.getParameter("password").equals("")) {
                    String login = req.getParameter("login");
                    String password = req.getParameter("password");

                    HttpSession session = req.getSession(true);


                    UserCredentials credentials = new UserCredentials(login, password);
                    session.setAttribute("credentials", credentials);
                    resp.sendRedirect( "userhome");
                } else {
                    resp.sendRedirect("login");
                }
            }


}
