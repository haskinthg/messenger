package dev.application.models.entities;

import dev.application.models.MessageStatus;
import dev.application.models.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    private String value;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
    private Date dateTime;

    @OneToOne
    private MessageEntity childMessage;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ChatEntity chat;


}
