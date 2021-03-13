package com.anpilogoff.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    private HttpSession httpSession;

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        Map<String, List<String>> headers = request.getHeaders();
        config.getUserProperties().put(HttpSession.class.getName(),headers.get("cookie"));

    }


}
