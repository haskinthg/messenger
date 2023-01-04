package dev.application.repositories;

import dev.application.models.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepo extends CrudRepository<MessageEntity, Long> {

    //    @Query(value = "select * from messages where id = :id and ", nativeQuery = true)
    public Set<MessageEntity> findByChatId(long id);

    public Set<MessageEntity> findByUserId(long id);

    public MessageEntity findById(long id);
}
