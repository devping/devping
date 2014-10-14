package org.jbug.devping.interfaces.ws;

import org.jbug.devping.service.WSSessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * Created by jhouse on 10/9/14.
 */
public class CSWebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private WSSessionManagerService cms;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payloadMessage = (String) message.getPayload();
        System.out.println("payloadMessage");
        System.out.println(payloadMessage);
        session.sendMessage(new TextMessage("ECHO : " + payloadMessage));
    }

    // Connection이 구성된 후, 호출되는 method
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);


       cms.put(session.getAttributes().get("userId").toString(), session);

        System.out.println("connected : " + session.getAttributes().get("userId").toString());


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Connection이 종료된 후, 호출되는 method
        super.afterConnectionClosed(session, status);

        cms.remove(session.getAttributes().get("userId").toString());

        System.out.println("connected : " + session.getAttributes().get("userId").toString());

    }


}
