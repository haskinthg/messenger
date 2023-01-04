package dev.application.repositories;

import dev.application.models.entities.ChatEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ChatRepo extends CrudRepository<ChatEntity, Long> {
    public Set<ChatEntity> findAll();

    public Optional<ChatEntity> findById(long id);

    public Set<ChatEntity> findByUsers_Username(String username);

}
