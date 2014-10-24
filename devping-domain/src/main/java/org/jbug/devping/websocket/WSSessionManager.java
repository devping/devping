package org.jbug.devping.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jhouse on 10/9/14.
 */
@Component
public class WSSessionManager {
    private final static Logger logger = LoggerFactory.getLogger(WSSessionManager.class);

    private static final Map<String,WebSocketSession> sessions = Collections.synchronizedMap(new HashMap<String,WebSocketSession>());

    public WebSocketSession get(String userId){
        return sessions.get(userId);
    }

    public void put(String userId, WebSocketSession session){
        if(logger.isDebugEnabled()){
            System.out.println("websocket session is added to ChannelManager for " + userId + " : session = " +session.getId());
        }
        System.out.println("websocket session is added to ChannelManager for " + userId + " : session = " +session.getId());

        sessions.put(userId,session);
        System.out.println(contain(userId));

    }


    public void remove(String userId) {
        if(logger.isDebugEnabled()){
            System.out.println("websocket session is removed from ChannelManager for " + userId + " : session = " + sessions.get(userId).getId());
        }
        System.out.println("websocket session is removed from ChannelManager for " + userId + " : session = " + sessions.get(userId).getId());

        sessions.remove(userId);
        System.out.println(contain(userId));
    }

    public boolean contain(String userId){
        return sessions.containsKey(userId);
    }
}

