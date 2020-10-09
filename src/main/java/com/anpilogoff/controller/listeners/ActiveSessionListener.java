package com.anpilogoff.controller.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
@WebListener
public class ActiveSessionListener implements HttpSessionListener {


    //todo: почему final
    public static final Map<String, HttpSession> sessions = new HashMap<>();


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessions.put(se.getSession().getId(),se.getSession());
        System.out.println("HttpSession created: "+ sessions.get(se.getSession().getId()));
        System.out.println("Active sessions: " + sessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessions.remove(se.getSession().getId());
        se.getSession().invalidate();
        System.out.println("Active sessions number: "+ sessions);
    }

}
