package org.jbug.devping.interfaces.ws;

import org.jbug.devping.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
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

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                UserVo userVo = (UserVo) session.getAttribute("userVo");
                attributes.put("userId", userVo.getUserId());
                System.out.println(userVo.getUserId());
            }
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("After Handshake");

    }
}
