package com.anpilogoff.controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class ActiveSessionListener implements HttpSessionListener {
private final static Logger log = Logger.getLogger(ActiveSessionListener.class);

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
        String session = se.getSession().getId();
        se.getSession().invalidate();
        log.info(session + " invalidated");
        System.out.println("Active sessions number: " + sessions);
        log.info("active sessions number: " + sessions.keySet());
    }

}
