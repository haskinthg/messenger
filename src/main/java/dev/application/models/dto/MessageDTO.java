package dev.application.models.dto;

import dev.application.models.MessageStatus;
import dev.application.models.MessageType;
import dev.application.models.entities.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private MessageType messageType;
    private String value;
    private MessageStatus status;
    private Date dateTime;
    private MessageEntity childMessage;
    private String usernameFrom;

    private String usernameTo;
    private Long chat_id;
    public MessageDTO(MessageEntity msgEntity) {
        this.id = msgEntity.getId();
        this.messageType = msgEntity.getMessageType();
        this.value = msgEntity.getValue();
        this.status = msgEntity.getStatus();
        this.dateTime = msgEntity.getDateTime();
        this.usernameFrom = msgEntity.getUser().getUsername();
        this.chat_id = msgEntity.getChat().getId();
    }
}
