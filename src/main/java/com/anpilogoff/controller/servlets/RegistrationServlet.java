package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.connection.DBconnector;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class RegistrationServlet extends HttpServlet {
    private UserDAO dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        dao = new UserDAO();
        dao.setConnectionBuilder(new DBconnector());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");

        User user = new User(login, password, nickname, email);

        user = dao.registerNewUser(user);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userNickname",user.getNickname());
         //   session.setAttribute("user", user);
            System.out.println("forward to rg profile html");
            req.getRequestDispatcher("registerprofile.html").forward(req,resp);
        }else resp.getWriter().write("sorry but user with same credentials are already registered");
    }
}
