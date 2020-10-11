package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.Profile;
import com.anpilogoff.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.util.Enumeration;

/**
 *That servlet will be use after servlet
 * @see RegistrationServlet which open a new session and add attribute "userNickname" in it, and role of
 * @see ProfileRegistrationServlet is to get "userNickname" attribute from earlier opened http-session as well as
 * "registerprofile.html" page parameters such as:
 * name, surname, age, gender, country.. and use them as income arguments in method of
 * @see UserDAO class
 * @see UserDAO#registerNewProfile(String, String, String, int, String, String)  method returnable result of which
 * will initialize object of
 * @see Profile class, and in case of success profile registration - client will be redirect on resource "/login"
 *
 */
public class ProfileRegistrationServlet extends HttpServlet {

    private UserDAO dao;
    private Logger log = null;

    @Override
    public void init() {
        dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
        log = Logger.getLogger(ProfileRegistrationServlet.class);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            if (req.getSession(false).getAttribute("userNickname") == null) {
                req.getSession(false).invalidate();

            }

            String nickname = (String) req.getSession(false).getAttribute("nickname");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            int age = Integer.parseInt(req.getParameter("age"));
            String gender = req.getParameter("gender");
            String country = req.getParameter("country");

            Profile profile = dao.registerNewProfile(nickname, name, surname, age, gender, country);
            if (profile != null) {
                log.info("profile created:  " + profile);
                req.getSession(false).invalidate();
                log.info("session invalidated " + req.getSession(false));
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
            } else {
                req.getRequestDispatcher(req.getServletContext().getContextPath() + "/registerprofile").forward(req, resp);
            }
        } catch (ServletException e) {
            System.out.println(e.getCause() + "  prof registr excep");
            req.getRequestDispatcher("registerprofile.html").forward(req,resp);
        }
    }
}



