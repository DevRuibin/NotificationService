package com.example.notificationserver;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = Long.parseLong(Objects.requireNonNull(session.getUri()).getQuery().split("=")[1]);
        sessions.put(userId, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.values().remove(session);
    }

    public void sendMessageToUser(Long userId, MessageModel message) {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


