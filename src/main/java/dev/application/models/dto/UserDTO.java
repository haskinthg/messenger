package dev.application.models.dto;

import dev.application.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.photo = userEntity.getPhoto();
    }
    private Long id;
    private String username;
    private String photo;
//    private Set<MessageDTO> messages;
}
