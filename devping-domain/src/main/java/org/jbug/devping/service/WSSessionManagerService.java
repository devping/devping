package org.jbug.devping.service;

import org.jbug.devping.websocket.WSSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by jhouse on 10/9/14.
 */
@Service
public class WSSessionManagerService {

    @Autowired
    WSSessionManager cm;

    public void put(String userId, WebSocketSession session){
        try {
            session.sendMessage(new TextMessage("test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cm.put(userId, session);
    }

    public WebSocketSession get(String userId){
        return cm.get(userId);
    }

    public void remove(String userId){
        cm.remove(userId);
    }
}
