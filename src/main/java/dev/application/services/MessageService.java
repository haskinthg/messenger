package dev.application.services;

import dev.application.models.MessageStatus;
import dev.application.models.dto.MessageDTO;
import dev.application.models.entities.MessageEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.MessageRepo;
import dev.application.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UserRepo userRepo;

    public List<MessageDTO> pageByChatId(Long id, int page, int size) throws Exception {
        return messageRepo.findByStatusNotAndChatId(MessageStatus.DELETED, id, PageRequest.of(page, size, Sort.by("dateTime").descending())).getContent()
                .stream().map(MessageDTO::new)
                .sorted(Comparator.comparing(MessageDTO::getDateTime))
                .collect(Collectors.toList());
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

    public MessageDTO deleteMessage(MessageDTO msg) {
        MessageEntity msgEntity = messageRepo.findById(msg.getId()).get();
        msgEntity.setStatus(MessageStatus.DELETED);
        return new MessageDTO(messageRepo.save(msgEntity));
    }

    public long CountByChatId(Long id) {
        return messageRepo.countByChatId(id);
    }

}
