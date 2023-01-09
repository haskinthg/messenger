package dev.application.services;

import dev.application.models.MessageStatus;
import dev.application.models.dto.MessageDTO;
import dev.application.models.entities.MessageEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.MessageRepo;
import dev.application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UserRepo userRepo;

    public Set<MessageDTO> allByChatId(Long id) throws Exception {
        return messageRepo.findByChatId(id).stream().map(MessageDTO::new).collect(Collectors.toSet());
    }

    public MessageDTO addMessage(MessageDTO msgDTO) {
        MessageEntity msgEntity = new MessageEntity();
        msgEntity.setStatus(MessageStatus.RECEIVED);
        msgEntity.setMessageType(msgDTO.getMessageType());
        msgEntity.setDateTime(new Date());
        msgEntity.setChat(chatRepo.findById(msgDTO.getChat_id()).get());
        msgEntity.setUser(userRepo.findByUsername(msgDTO.getUsernameFrom()));
        msgEntity.setValue(msgDTO.getValue());
        if (msgDTO.getChildMessage() != null)
            if (messageRepo.findById(msgDTO.getChildMessage().getId()).isPresent())
                msgEntity.setChildMessage(messageRepo.findById(msgDTO.getChildMessage().getId()).get());
        return new MessageDTO(messageRepo.save(msgEntity));
    }

}
