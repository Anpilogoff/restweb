package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.Profile;
import com.anpilogoff.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ProfileRegistrationServlet extends HttpServlet {
    private UserDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector() );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registerprofile.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getSession(false).getAttribute("userNickname"));
        String nickname = (String) req.getSession(false).getAttribute("userNickname");

        if (req.getSession(false).getAttribute("userNickname") != null) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            int age = Integer.parseInt(req.getParameter("age"));
            String gender = req.getParameter("gender");
            String country = req.getParameter("country");

            Profile profile = dao.registerNewProfile(nickname, name, surname, age, gender, country);

          //  User user = (User) req.getSession(false).getAttribute("user");

            //System.out.println(user + "     userfrom profile reg serv");
            System.out.println(profile + "   by usrerdao");

            req.getSession(false).invalidate();
            resp.sendRedirect("login");
        }


    }
}
