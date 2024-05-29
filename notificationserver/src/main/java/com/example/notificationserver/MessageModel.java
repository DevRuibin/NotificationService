package com.example.notificationserver;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageModel implements Serializable  {
    @Id
    @GeneratedValue
    private Long id;
    private Long senderId;
    private Long toUserId;
    private String text;
    private Long timestamp;
    private boolean read;
}

