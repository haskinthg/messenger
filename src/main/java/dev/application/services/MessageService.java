package dev.application.services;

import dev.application.models.MessageStatus;
import dev.application.models.dto.MessageDTO;
import dev.application.models.entities.MessageEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.MessageRepo;
import dev.application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    private ChatRepo chatRepo;
    private UserRepo userRepo;

    public Set<MessageDTO> allByUserId(Long id) throws Exception {
        return messageRepo.findByChatId(id).stream().map(MessageDTO::new).collect(Collectors.toSet());
    }

    public MessageDTO addMessage(MessageDTO msgDTO) {
        MessageEntity msgEntity = new MessageEntity();
        msgEntity.setId(msgDTO.getId());
        msgEntity.setStatus(MessageStatus.RECEIVED);
        msgEntity.setMessageType(msgDTO.getMessageType());
        msgEntity.setDateTime(new Date());
        msgEntity.setChat(chatRepo.findById(msgDTO.getChat_id()).get());
        msgEntity.setUser(userRepo.findByUsername(msgDTO.getUsernameFrom()));
        return msgDTO;
    }

}
