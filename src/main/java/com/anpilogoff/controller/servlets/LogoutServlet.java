package com.anpilogoff.controller.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *Just base on servlet name - his main role is to switch user's "online" status to "offline"
 * through session invalidating and redirect him to "login.html" page
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession(false).invalidate();
        resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
    }
}
