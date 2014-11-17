package org.jbug.devping.interfaces.ws;

import org.jbug.devping.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by jhouse on 10/11/14.
 */
public class SessionInterceptor implements HandshakeInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
       logger.debug("Before Handshake");
       System.out.println("Before Handshake");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object o = (auth != null) ? auth.getPrincipal() :  null;

        UserVo user = null;

        if (o != null && o instanceof UserVo) {
            user = (UserVo) o;
            attributes.put("userId", user.getUserId());
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("After Handshake");

    }
}
