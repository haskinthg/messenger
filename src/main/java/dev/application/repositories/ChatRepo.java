package dev.application.repositories;

import dev.application.models.entities.ChatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ChatRepo extends CrudRepository<ChatEntity, Long> {
    Set<ChatEntity> findAll();

    Optional<ChatEntity> findById(long id);

    Set<ChatEntity> findByUsers_Username(String username);

    @Query(value="select ch.* from users u \n" +
            "inner join user_chat uc on u.id = uc.user_id \n" +
            "inner join chats ch on ch.id = uc.chat_id\n" +
            "where username in (:un1, :un2)\n" +
            "group by ch.id\n" +
            "having count(ch.id) = 2", nativeQuery = true)
    ChatEntity findBy2Usernames(String un1, String un2);

}
