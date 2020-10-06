package com.anpilogoff.websocket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * WebSocket endpoint for the chat messages.
 */
@ServerEndpoint(value = "/chat")
public class ChatEndpoint {
    private static Map<RemoteEndpoint.Basic, Session> sessions = Collections.synchronizedMap(new HashMap<>());

    @OnMessage
    public void onMessage(String message, Session session){
        message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + message;

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {
                s.getAsyncRemote().sendText(message);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessions.put(session.getBasicRemote(), session);
        for (int i = 0; i <= sessions.size() ; i++) {
            if(i == 0){
                System.out.println("opened sessions");
            }
            for (RemoteEndpoint.Basic endpoint : sessions.keySet()) {
                System.out.println(endpoint);
            }
        }

        for (RemoteEndpoint.Basic session1 : sessions.keySet()) {
            session1.sendText("Connected: " + session.getBasicRemote().toString());

        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("opened session: "+ sessions);
        sessions.remove(session.getBasicRemote());

    }
}



