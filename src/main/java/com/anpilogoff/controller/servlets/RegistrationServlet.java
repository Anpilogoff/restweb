package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Receives registration request parameters and creates a user object, inserted as an argument into
 *  @see UserDAO # registerNewUser (User) method, which in case of creating a new user based on request parameters will
 *  open new HttpSession and set "nickname" attribute will be used in
 *  @see UserDAO#registerNewProfile(String, String, String, int, String, String)  called inside
 * @see ProfileRegistrationServlet#doPost(HttpServletRequest, HttpServletResponse) method as foreign key  ...
 *
 */
public class RegistrationServlet extends HttpServlet {
    private UserDAO dao;
    private Logger log = null;


/**
 * just forward incoming request to registration.html processing */
@Override
    public void init() {
        dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
        log = Logger.getLogger(RegistrationServlet.class);
    }

    /**
     * creates a new user based on request parameters and inserts him as argument in
     * @see UserDAO#registerNewUser(User) method  will taking attempt to create new user with unique nickname and login
     * received from
     * @param req object parameters using
     * @see HttpSession#getAttribute(String) method */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");
        User user = new User(login, password, nickname, email,"user");
        user = dao.registerNewUser(user);
        if (user != null) {
            log.info("New user registered:  " + user);
            HttpSession session = req.getSession(true);
            session.setAttribute("userNickname", user.getNickname());

            req.getRequestDispatcher("registerprofile.html").forward(req,resp);
        }else {
            resp.getWriter().write("sorry but user with same credentials are already registered");
            resp.sendRedirect("login");
        }
    }
}
