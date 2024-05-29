package com.example.notificationserver;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(MessageModel message) {
        messageRepository.save(message);
        rabbitTemplate.convertAndSend("messageQueue", message);
    }

    public void markAsRead(Long id) {
        messageRepository.findById(id).ifPresent(message -> {
            message.setRead(true);
            messageRepository.save(message);
        });
    }


    public List<MessageModel> getMessagesByUserID(Long userId) {
        return messageRepository.findByToUserIdOrSenderId(userId, userId)
                .orElse(List.of());
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
