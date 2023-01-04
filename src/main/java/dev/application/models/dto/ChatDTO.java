package dev.application.models.dto;

import dev.application.models.ChatStatus;
import dev.application.models.entities.ChatEntity;
import lombok.*;

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
        this.messages = chat.getMessages().stream().map(MessageDTO::new).collect(Collectors.toSet());
        this.chatStatus = chat.getChatStatus();
    }
    private Long id;
    private Set<UserDTO> users;
    private Set<MessageDTO> messages;
    private ChatStatus chatStatus;
//    private String chat_name;
//    private String chat_photo;
}
