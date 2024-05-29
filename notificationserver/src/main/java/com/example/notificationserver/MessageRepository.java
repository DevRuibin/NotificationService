package com.example.notificationserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long>{
    Optional<List<MessageModel>> findByToUserIdOrSenderId(Long toUserId, Long senderId);
}
