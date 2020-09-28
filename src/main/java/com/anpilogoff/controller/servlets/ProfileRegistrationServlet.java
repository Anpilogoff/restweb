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
import java.util.Enumeration;

/**
 *
 */
public class ProfileRegistrationServlet extends HttpServlet {

    private UserDAO dao;
    private  Logger log = null;

    @Override
    public void init() {
        dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
        log = Logger.getLogger(ProfileRegistrationServlet.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registerprofile.html").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) != null) {
            if (req.getSession(false).getAttribute("userNickname") != null) {
               String nickname = (String) req.getSession(false).getAttribute("userNickname");
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                int age = Integer.parseInt(req.getParameter("age"));
                String gender = req.getParameter("gender");
                String country = req.getParameter("country");

                Profile profile = dao.registerNewProfile(nickname, name, surname, age, gender, country);
                if (profile != null) {
                    log.info("profile created:  "+ profile);
                    req.getSession(false).invalidate();
                    resp.sendRedirect("login");
                } else {req.getRequestDispatcher(req.getServletContext().getContextPath() + "registerprofile");}
            }
        } else if (req.getSession(false) != null && req.getSession(false).getAttribute("userNickname") == null) {
            req.getSession(false).invalidate();
            resp.sendRedirect(req.getServletContext().getContextPath() + "login");
        }
    }
}

