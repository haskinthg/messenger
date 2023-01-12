package dev.application.models.dto;

import dev.application.models.ChatStatus;
import dev.application.models.entities.ChatEntity;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    public ChatDTO(ChatEntity chat) {
        this.id = chat.getId();
        this.users = chat.getUsers().stream().map(UserDTO::new).collect(Collectors.toSet());
//        this.messages = chat.getMessages().stream().map(MessageDTO::new).collect(Collectors.toSet());
        if(chat.getMessages()!=null)
        if(chat.getMessages().stream().reduce((prev, next) -> next).isPresent())
            this.lastMessage = new MessageDTO(chat.getMessages().stream().reduce((prev, next) -> next).get());
        this.chatStatus = chat.getChatStatus();
    }

    public Date getDateLast() {
        if(this.lastMessage==null) return null;
        if(this.lastMessage.getDateTime()==null)
            this.lastMessage.getDateTime();
        return this.lastMessage.getDateTime();
    }
    private Long id;
    private Set<UserDTO> users;
//    private Set<MessageDTO> messages;

    private MessageDTO lastMessage;
    private ChatStatus chatStatus;
//    private String chat_name;
//    private String chat_photo;
}
