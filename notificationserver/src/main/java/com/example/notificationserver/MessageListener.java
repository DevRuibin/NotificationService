package com.example.notificationserver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MyWebSocketHandler myWebSocketHandler;

    public MessageListener(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @RabbitListener(queues = "messageQueue")
    public void handleMessage(MessageModel message) {
        myWebSocketHandler.sendMessageToUser(message.getToUserId(), message);
    }
}



