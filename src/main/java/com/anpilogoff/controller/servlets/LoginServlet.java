package com.anpilogoff.controller.servlets;

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
    Logger log = Logger.getLogger(LoginServlet.class);

    /**
     * @param request  is object created by Tomcat after request receiving and initialized with request attributes
     * @param response is empty-object created by Tomcat after request receiving(will be initialized when method
     * @see HttpServlet#service(ServletRequest, ServletResponse) will be called */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {

            session = request.getSession(true);
            session.setAttribute("authStatus", "non authorized");
            request.getRequestDispatcher("login.html").forward(request, response);
        }


        if (session.getAttribute("authStatus").equals("non authorized")) {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    /**
     * @param req  is object created by Tomcat after request receiving and initialized with request attributes
     * @param resp is empty-object created by Tomcat after request receiving(will be initialized when method
     * @see HttpServlet#service(ServletRequest, ServletResponse) will be called */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            if (session.getAttribute("authStatus").equals("non authorized")) {
                if (req.getParameter("login") != null && req.getParameter("password") != null) {
                    String login = req.getParameter("login");
                    String password = req.getParameter("password");

                    //todo: dao logic|| comparing parameters with data in db and if compare ++ ->
                    //todo: +++ encrypt password

                    UserCredentials credentials = new UserCredentials(login, password);
                    session.setAttribute("credentials", credentials);
                    session.removeAttribute("authStatus");
                    session.setAttribute("authStatus", "authorized");
                    resp.sendRedirect( req.getServletContext().getContextPath() +"/userhome");
                } else {
                    resp.sendRedirect("login");
                }
            } else if (!session.getAttribute("authStatus").equals("non authorized")) {
                session.invalidate();
                resp.sendRedirect(req.getServletContext().getContextPath() + " /login");
            }
        }
    }
}