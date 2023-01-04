package dev.application.repositories;

import dev.application.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    public Set<UserEntity> findAll();

    public UserEntity findByUsername(String username);
}