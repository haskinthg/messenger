package dev.application.repositories;

import dev.application.models.MessageStatus;
import dev.application.models.entities.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MessageRepo extends PagingAndSortingRepository<MessageEntity, Long> {

    //    @Query(value = "select * from messages where id = :id and ", nativeQuery = true)
    Page<MessageEntity> findByStatusNotAndChatId(MessageStatus status, long id, Pageable pageable);

    long countByChatId(long id);

//    public Set<MessageEntity> findByUserId(long id);

    Optional<MessageEntity> findById(long id);
}
